package br.curitiba.android.mviarch.features.browser

import br.curitiba.android.mviarch.data.ProjectsRepository
import br.curitiba.android.mviarch.data.models.Project
import br.curitiba.android.mviarch.features.browser.mvi.BrowserIntent
import br.curitiba.android.mviarch.util.FakeFactory
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.observers.TestObserver
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class BrowserViewModelTest {

    private lateinit var viewModel: BrowserViewModel

    @Mock
    private lateinit var projectRepository: ProjectsRepository
    private lateinit var scheduler: Scheduler
    private lateinit var testObserver: TestObserver<BrowserViewState>
    private lateinit var projects: List<Project>

    @Before
    fun setup() {
        // Mockito has a very convenient way to inject mocks by using the @Mock annotation. To
        // inject the mocks in the test the initMocks method needs to be called.
        MockitoAnnotations.initMocks(this)

        // Make the sure that all schedulers are immediate.
        scheduler = Schedulers.trampoline()

        // Get a reference to the class under test
        viewModel = BrowserViewModel(projectRepository, scheduler, scheduler)

        testObserver = viewModel.states().test()

        projects = listOf(
            FakeFactory.randomProject(),
            FakeFactory.randomProject(),
            FakeFactory.randomProject(),
            FakeFactory.randomProject(),
            FakeFactory.randomProject()
        )
    }

    @Test
    fun `it should load projects from repository and update view state properly`() {
        // Given an initialized viewModel
        whenever(projectRepository.getProjects()).thenReturn(Single.just(projects))
        // When loading projects is initiated
        viewModel.processIntents(Observable.just(BrowserIntent.InitialIntent))

        // Then it should receive no errors
        testObserver.assertNoErrors()
        // And 2 values:
        testObserver.assertValueCount(2)
        // One to show the loading indicator
        testObserver.assertValueAt(0) {
            it.isLoading && it.error == null && it.projects.isEmpty()
        }
        // And other hiding it and showing the list of projects
        testObserver.assertValueAt(1) {
            !it.isLoading && it.error == null && it.projects.containsAll(projects)
        }
    }
}
