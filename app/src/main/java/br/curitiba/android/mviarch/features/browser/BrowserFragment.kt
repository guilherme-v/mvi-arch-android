package br.curitiba.android.mviarch.features.browser

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import br.curitiba.android.mviarch.MviApplication
import br.curitiba.android.mviarch.R
import br.curitiba.android.mviarch.di.HasInjectors
import br.curitiba.android.mviarch.features.browser.mvi.BrowserIntent
import com.jakewharton.rxbinding3.swiperefreshlayout.refreshes
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_browser.*
import javax.inject.Inject

class BrowserFragment : Fragment() {

    @Inject lateinit var viewModel: BrowserViewModel

    private val browseAdapter by lazy { BrowserAdapter() }
    private val disposables by lazy { CompositeDisposable() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_browser, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBrowseRecycler()
        disposables.add(viewModel.states().subscribe(::render))
        viewModel.processIntents(intents())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        (activity?.application as HasInjectors).fragmentInjector.inject(this)
    }

    private fun setupBrowseRecycler() {
        list.layoutManager = LinearLayoutManager(context)
        list.adapter = browseAdapter

//        browseAdapter.projectListener = object : ProjectItemListener {
//            override fun onBookmarkedProjectClicked(projectId: String) {
//                viewModel.unbookmarkProject(projectId)
//            }
//            override fun onProjectClicked(projectId: String) {
//                viewModel.bookmarkProject(projectId)
//            }
//        }
    }

    private fun initialIntent() = Observable.just(BrowserIntent.InitialIntent)

    private fun refreshIntent() = swipe.refreshes().map { BrowserIntent.RefreshIntent }
    fun intents() = Observable.merge<BrowserIntent>(
        initialIntent(),
        refreshIntent()
    )

    fun render(state: BrowserViewState) {
        if (state.projects.isNotEmpty()) browseAdapter.setList(state.projects)
        browseAdapter.notifyDataSetChanged()
        swipe.isRefreshing = state.isLoading
        progress.visibility = if (state.projects.isEmpty()) View.VISIBLE else View.GONE
    }
}
