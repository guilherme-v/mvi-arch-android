package br.curitiba.android.mviarch.features.browser.mvi

import br.curitiba.android.mviarch.data.models.Project

sealed class BrowserIntent {
    object InitialIntent: BrowserIntent()
    object RefreshIntent: BrowserIntent()
    data class BookmarkProjectIntent(val project: Project): BrowserIntent()
    data class UnBookmarkProjectIntent(val project: Project): BrowserIntent()
}