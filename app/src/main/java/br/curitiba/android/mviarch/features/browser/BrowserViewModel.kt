package br.curitiba.android.mviarch.features.browser

import androidx.lifecycle.ViewModel
import br.curitiba.android.mviarch.data.source.ProjectsDataSource
import br.curitiba.android.mviarch.features.browser.mvi.BrowserAction
import br.curitiba.android.mviarch.features.browser.mvi.BrowserAction.*
import br.curitiba.android.mviarch.features.browser.mvi.BrowserIntent
import br.curitiba.android.mviarch.features.browser.mvi.BrowserIntent.*
import br.curitiba.android.mviarch.features.browser.mvi.BrowserResult
import br.curitiba.android.mviarch.features.browser.mvi.BrowserResult.*
import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

class BrowserViewModel @Inject constructor(
    private val projectsRepository: ProjectsDataSource
) : ViewModel() {

    private val intentsSubject: PublishSubject<BrowserIntent> = PublishSubject.create()
    private val statesSubject: PublishSubject<BrowserViewState> = PublishSubject.create()

    private val actionProcessor by lazy {
        ObservableTransformer<BrowserAction, BrowserResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(LoadProjectsAction::class.java).compose(loadProjectsProcessor),
                    shared.ofType(BookmarkProjectAction::class.java).compose(bookmarkProjectProcessor),
                    shared.ofType(UnBookmarkProjectAction::class.java).compose(unBookmarkProjectProcessor)
                )
            }
        }
    }

    private val loadProjectsProcessor by lazy {
        ObservableTransformer<LoadProjectsAction, BrowserResult> { actions ->
            actions.flatMap {
                projectsRepository
                    .getProjects()
                    .toObservable()
                    .map { projectList -> LoadProjectsResult.Success(projectList) }
                    .cast(LoadProjectsResult::class.java)
                    .onErrorReturn(LoadProjectsResult::Failure)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(LoadProjectsResult.InFlight)
            }
        }
    }

    private val bookmarkProjectProcessor by lazy {
        ObservableTransformer<BookmarkProjectAction, BrowserResult> { actions ->
            actions.flatMap { action ->
                projectsRepository
                    .bookmarkProject(action.project.id!!)
                    .andThen(Observable.just(BookmarkProjectResult.Success))
                    .cast(BookmarkProjectResult::class.java)
                    .onErrorReturn(BookmarkProjectResult::Failure)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(BookmarkProjectResult.InFlight)
            }
        }
    }

    private val unBookmarkProjectProcessor by lazy {
        ObservableTransformer<UnBookmarkProjectAction, BrowserResult> { actions ->
            actions.flatMap { action ->
                projectsRepository
                    .unbookmarkProject(action.project.id!!)
                    .andThen(Observable.just(UnBookmarkProjectResult.Success))
                    .cast(UnBookmarkProjectResult::class.java)
                    .onErrorReturn(UnBookmarkProjectResult::Failure)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .startWith(UnBookmarkProjectResult.InFlight)
            }
        }
    }

    private val reducer by lazy {
        BiFunction { previousState: BrowserViewState, result: BrowserResult ->
            when (result) {
                is LoadProjectsResult -> reduceLoadProjectResult(result, previousState)
                is BookmarkProjectResult -> reduceBookmarkProjectResult(result, previousState)
                is UnBookmarkProjectResult -> reduceUnBookmarkProjectResult(result, previousState)
            }
        }
    }

    init {
        intentsSubject
            .map { actionFromIntent(it) }
            .compose(actionProcessor)
            .scan(BrowserViewState.idle(), reducer)
            .subscribe(statesSubject)
    }

    fun processIntents(intents: Observable<BrowserIntent>) {
        intents.subscribe(intentsSubject)
    }

    fun states() = statesSubject

    private fun actionFromIntent(intent: BrowserIntent): BrowserAction = when (intent) {
        InitialIntent -> LoadProjectsAction
        RefreshIntent -> LoadProjectsAction
        is BookmarkProjectIntent -> BookmarkProjectAction(intent.project)
        is UnBookmarkProjectIntent -> UnBookmarkProjectAction(intent.project)
    }

    private fun reduceUnBookmarkProjectResult(result: UnBookmarkProjectResult, previousState: BrowserViewState) =
        when (result) {
            UnBookmarkProjectResult.InFlight -> previousState.copy(isLoading = true)
            is UnBookmarkProjectResult.Failure -> previousState.copy(isLoading = false, error = result.error)
            UnBookmarkProjectResult.Success -> TODO()
        }


    private fun reduceBookmarkProjectResult(result: BookmarkProjectResult, previousState: BrowserViewState) =
        when (result) {
            BookmarkProjectResult.InFlight -> previousState.copy(isLoading = true)
            is BookmarkProjectResult.Failure -> previousState.copy(isLoading = false, error = result.error)
            BookmarkProjectResult.Success -> TODO()
        }

    private fun reduceLoadProjectResult(result: LoadProjectsResult, previousState: BrowserViewState) =
        when (result) {
            LoadProjectsResult.InFlight -> previousState.copy(isLoading = true)
            is LoadProjectsResult.Failure -> previousState.copy(isLoading = false, error = result.error)
            is LoadProjectsResult.Success -> previousState.copy(
                isLoading = false,
                error = null,
                projects = result.projects
            )
        }
}
