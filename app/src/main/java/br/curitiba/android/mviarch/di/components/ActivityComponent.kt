package br.curitiba.android.mviarch.di.components

import br.curitiba.android.mviarch.MainActivity
import dagger.Subcomponent

@Subcomponent
interface ActivityComponent {

    fun inject(activity: MainActivity)
}