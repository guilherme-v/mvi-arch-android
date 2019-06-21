package br.curitiba.android.mviarch

import android.app.Application
import br.curitiba.android.mviarch.di.components.DaggerApplicationComponent
import com.facebook.stetho.Stetho

class MviApplication : Application() {

    val applicationComponent by lazy {
        DaggerApplicationComponent.builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        applicationComponent.injectApplication(this)
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}