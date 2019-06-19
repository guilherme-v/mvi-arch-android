package br.curitiba.android.mviarch.di.components

import br.curitiba.android.mviarch.features.browser.BrowserFragment
import dagger.Subcomponent

@Subcomponent
interface FragmentComponent {

    fun inject(fragment: BrowserFragment)
}