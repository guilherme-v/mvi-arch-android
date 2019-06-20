package br.curitiba.android.mviarch.features.browser.mvi

import br.curitiba.android.mviarch.data.models.Project

sealed class BrowserAction {
    object LoadProjectsAction : BrowserAction()
    data class BookmarkProjectAction(val project: Project) : BrowserAction()
    data class UnBookmarkProjectAction(val project: Project) : BrowserAction()
}