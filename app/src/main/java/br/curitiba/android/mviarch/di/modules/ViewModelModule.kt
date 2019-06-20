package br.curitiba.android.mviarch.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.curitiba.android.mviarch.di.factories.ViewModelFactory
import br.curitiba.android.mviarch.di.factories.ViewModelMapKey
import br.curitiba.android.mviarch.features.browser.BrowserViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module(includes = [ViewModelModule.BindsModule::class])
class ViewModelModule {

    @Module
    interface BindsModule {

        @Binds
        fun viewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

        @Binds
        @IntoMap
        @ViewModelMapKey(BrowserViewModel::class)
        fun signInViewModel(viewModel: BrowserViewModel): ViewModel
    }
}