package br.curitiba.android.mviarch.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class ApplicationContext

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class NetworkProjectsDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class DatabaseProjectsDataSource

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class UIScheduler

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class IOScheduler