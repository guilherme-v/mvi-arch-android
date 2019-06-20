package br.curitiba.android.mviarch.features.browser

import br.curitiba.android.mviarch.data.models.Project

data class BrowserViewState(
    val isLoading: Boolean,
    val projects: List<Project>,
    val error: Throwable?
) {
    companion object {
        fun idle() = BrowserViewState(
            isLoading = true,
            projects = emptyList(),
            error = null
        )
    }
}