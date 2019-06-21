package br.curitiba.android.mviarch.data

import br.curitiba.android.mviarch.data.models.Project
import br.curitiba.android.mviarch.data.source.ProjectsDataSource
import br.curitiba.android.mviarch.di.qualifiers.DatabaseProjectsDataSource
import br.curitiba.android.mviarch.di.qualifiers.NetworkProjectsDataSource
import io.reactivex.Single
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor(
    @NetworkProjectsDataSource private val remote: ProjectsDataSource,
    @DatabaseProjectsDataSource private val local: ProjectsDataSource
) : ProjectsRepository {

    override fun getProjects(): Single<List<Project>> {
        return remote.getProjects()
            .flatMap {
                local.saveProjects(it)
                    .andThen(Single.defer { local.getProjects() })
            }
            .onErrorResumeNext { local.getProjects() } // load from DB if any error occurs (e.g.: offline)
    }
}