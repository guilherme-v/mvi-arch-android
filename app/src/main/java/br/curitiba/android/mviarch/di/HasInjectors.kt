package br.curitiba.android.mviarch.di

import br.curitiba.android.mviarch.di.components.ActivityComponent
import br.curitiba.android.mviarch.di.components.FragmentComponent

interface HasInjectors {

    val fragmentInjector: FragmentComponent
    val activityInjector: ActivityComponent
}