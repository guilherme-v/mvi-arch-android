package br.curitiba.android.mviarch.features.browser.mvi

import br.curitiba.android.mviarch.data.models.Project

sealed class BrowserResult {

    sealed class LoadProjectsResult : BrowserResult() {
        data class Success(val projects: List<Project>) : LoadProjectsResult()
        data class Failure(val error: Throwable) : LoadProjectsResult()
        object InFlight : LoadProjectsResult()
    }

    sealed class BookmarkProjectResult : BrowserResult() {
        object Success: BookmarkProjectResult()
        data class Failure(val error: Throwable) : BookmarkProjectResult()
        object InFlight : BookmarkProjectResult()
    }

    sealed class UnBookmarkProjectResult : BrowserResult() {
        object Success: UnBookmarkProjectResult()
        data class Failure(val error: Throwable) : UnBookmarkProjectResult()
        object InFlight : UnBookmarkProjectResult()
    }
}