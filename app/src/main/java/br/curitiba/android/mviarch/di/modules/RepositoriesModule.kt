package br.curitiba.android.mviarch.di.modules

import br.curitiba.android.mviarch.data.repositories.ProjectsRepository
import br.curitiba.android.mviarch.data.repositories.ProjectsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoriesModule.BindsModule::class])
class RepositoriesModule {

    @Module
    interface BindsModule {

        @Binds
        fun projectRepository(projectsRepository: ProjectsRepositoryImpl): ProjectsRepository
    }
}