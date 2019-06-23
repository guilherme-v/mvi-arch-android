package br.curitiba.android.mviarch.di.components

import android.app.Application
import br.curitiba.android.mviarch.TestApplication
import br.curitiba.android.mviarch.di.modules.TestDataModule
import br.curitiba.android.mviarch.di.modules.TestSchedulersModule
import br.curitiba.android.mviarch.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component

@Component(modules = [
    ViewModelModule::class,
    TestSchedulersModule::class,
    TestDataModule::class
])
interface TestApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestApplicationComponent
    }

    fun injectApplication(application: TestApplication)

    fun activityComponent(): ActivityComponent

    fun fragmentComponent(): FragmentComponent
}