package br.curitiba.android.mviarch.di.components

import android.app.Application
import android.content.Context
import br.curitiba.android.mviarch.MviApplication
import br.curitiba.android.mviarch.di.modules.ViewModelModule
import br.curitiba.android.mviarch.di.qualifiers.ApplicationContext
import br.curitiba.android.mviarch.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [
    ViewModelModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        @BindsInstance
        @ApplicationContext
        fun applicationContext(context: Context): Builder

        fun build(): ApplicationComponent
    }

    fun injectApplication(application: MviApplication)
}