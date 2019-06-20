package br.curitiba.android.mviarch.di.modules

import br.curitiba.android.mviarch.BuildConfig
import br.curitiba.android.mviarch.data.source.ProjectsDataSource
import br.curitiba.android.mviarch.data.ProjectsRepository
import br.curitiba.android.mviarch.data.source.remote.RemoteProjectsDataSource
import br.curitiba.android.mviarch.data.source.remote.service.GithubTrendingService
import br.curitiba.android.mviarch.data.source.remote.service.GithubTrendingServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module(includes = [DataModule.BindsModule::class])
class DataModule {

    @Module
    interface BindsModule {

        @Binds
        fun projectRemoteDataSource(remoteProjectsDataSource: RemoteProjectsDataSource): ProjectsDataSource

//        @Binds
//        fun projectRepository(projectsRepository: ProjectsRepository): ProjectsRepository
    }

    @Provides
    fun provideGithubService(): GithubTrendingService {
        return GithubTrendingServiceFactory.makeGithubTrendingService(BuildConfig.DEBUG)
    }
}