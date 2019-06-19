package br.curitiba.android.mviarch.di.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.curitiba.android.mviarch.di.scopes.ApplicationScope
import java.security.InvalidKeyException
import javax.inject.Inject
import javax.inject.Provider

@ApplicationScope
@Suppress("ConvertSecondaryConstructorToPrimary", "unused", "applicationContext", "UNCHECKED_CAST")
open class ViewModelFactory : ViewModelProvider.Factory {

    private val clazzProviderMap: Map<Class<out ViewModel>, Provider<ViewModel>>

    @Inject constructor(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) {
        this.clazzProviderMap = creators
    }

    override fun <T : ViewModel?> create(clazz: Class<T>): T {
        try {
            return clazzProviderMap[clazz]!!.get() as T
        } catch (e: Exception) {
            throw ViewModelFactoryException("No ViewModelProvider registered to $clazz")
        }
    }

    private class ViewModelFactoryException(details: String) : InvalidKeyException(details)
}
