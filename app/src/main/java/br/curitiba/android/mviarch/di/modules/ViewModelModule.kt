package br.curitiba.android.mviarch.di.modules

import androidx.lifecycle.ViewModelProvider
import br.curitiba.android.mviarch.di.factories.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module(includes = [ViewModelModule.BindsModule::class])
class ViewModelModule {

    @Module
    interface BindsModule {

        @Binds
        fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
    }
}