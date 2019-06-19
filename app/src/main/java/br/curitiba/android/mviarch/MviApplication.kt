package br.curitiba.android.mviarch

import android.app.Application
import br.curitiba.android.mviarch.di.components.DaggerApplicationComponent

class MviApplication : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .applicationContext(this)
            .build()
    }

    override fun onCreate() {
        applicationComponent.injectApplication(this)
        super.onCreate()
    }
}