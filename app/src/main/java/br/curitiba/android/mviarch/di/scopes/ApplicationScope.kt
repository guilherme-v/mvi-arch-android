package br.curitiba.android.mviarch.di.scopes

import javax.inject.Scope

/*
* Custom scope for global application context
* DO NOT USE @SINGLETON! (It is only a singleton inside a component)
* */
@Scope
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationScope