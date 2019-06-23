package br.curitiba.android.mviarch

import android.app.Application
import br.curitiba.android.mviarch.di.HasInjectors
import br.curitiba.android.mviarch.di.components.DaggerTestApplicationComponent

class TestApplication : Application(), HasInjectors {

    private val applicationComponent by lazy {
        DaggerTestApplicationComponent.builder()
            .application(this)
            .build()
    }

    override val activityInjector = applicationComponent.activityComponent()

    override val fragmentInjector = applicationComponent.fragmentComponent()

    override fun onCreate() {
        applicationComponent.injectApplication(this)
        super.onCreate()
    }
}