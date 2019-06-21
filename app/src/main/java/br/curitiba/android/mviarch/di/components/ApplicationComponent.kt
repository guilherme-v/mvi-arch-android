package br.curitiba.android.mviarch.di.components

import android.app.Application
import android.content.Context
import br.curitiba.android.mviarch.MviApplication
import br.curitiba.android.mviarch.di.modules.ApplicationModule
import br.curitiba.android.mviarch.di.modules.DataModule
import br.curitiba.android.mviarch.di.modules.SchedulersModule
import br.curitiba.android.mviarch.di.modules.ViewModelModule
import br.curitiba.android.mviarch.di.qualifiers.ApplicationContext
import br.curitiba.android.mviarch.di.scopes.ApplicationScope
import dagger.BindsInstance
import dagger.Component

@ApplicationScope
@Component(modules = [
    ApplicationModule::class,
    SchedulersModule::class,
    ViewModelModule::class,
    DataModule::class
])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun injectApplication(application: MviApplication)

    fun activityComponent(): ActivityComponent

    fun fragmentComponent(): FragmentComponent
}