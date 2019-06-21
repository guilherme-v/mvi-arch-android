package br.curitiba.android.mviarch.di.modules

import br.curitiba.android.mviarch.BuildConfig
import br.curitiba.android.mviarch.data.ProjectsRepository
import br.curitiba.android.mviarch.data.ProjectsRepositoryImpl
import br.curitiba.android.mviarch.data.source.ProjectsDataSource
import br.curitiba.android.mviarch.data.source.local.LocalProjectsDataSource
import br.curitiba.android.mviarch.data.source.remote.RemoteProjectsDataSource
import br.curitiba.android.mviarch.data.source.remote.service.GithubTrendingService
import br.curitiba.android.mviarch.data.source.remote.service.GithubTrendingServiceFactory
import br.curitiba.android.mviarch.di.qualifiers.DatabaseProjectsDataSource
import br.curitiba.android.mviarch.di.qualifiers.IOScheduler
import br.curitiba.android.mviarch.di.qualifiers.NetworkProjectsDataSource
import br.curitiba.android.mviarch.di.qualifiers.UIScheduler
import dagger.Binds
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@Module(includes = [DataModule.BindsModule::class])
class DataModule {

    @Module
    interface BindsModule {

        @Binds
        fun projectRepository(projectsRepository: ProjectsRepositoryImpl): ProjectsRepository

        @Binds
        @DatabaseProjectsDataSource
        fun bindLocalProjectsDataSource(datasource: LocalProjectsDataSource): ProjectsDataSource

        @Binds
        @NetworkProjectsDataSource
        fun bindRemoteeProjectsDataSource(datasource: RemoteProjectsDataSource): ProjectsDataSource

    }

    @Provides
    fun provideGithubService(): GithubTrendingService {
        return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
    }
}