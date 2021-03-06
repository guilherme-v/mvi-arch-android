package br.curitiba.android.mviarch

import android.app.Application
import br.curitiba.android.mviarch.di.HasInjectors
import br.curitiba.android.mviarch.di.components.DaggerApplicationComponent
import com.facebook.stetho.Stetho

class MviApplication : Application(), HasInjectors {

    private val applicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    override val activityInjector = applicationComponent.activityComponent()

    override val fragmentInjector = applicationComponent.fragmentComponent()

    override fun onCreate() {
        applicationComponent.injectApplication(this)
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}
