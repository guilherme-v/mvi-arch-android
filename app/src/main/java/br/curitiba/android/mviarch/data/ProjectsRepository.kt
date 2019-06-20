package br.curitiba.android.mviarch.data

import br.curitiba.android.mviarch.data.source.ProjectsDataSource
import br.curitiba.android.mviarch.data.models.Project
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsRepository @Inject constructor(
    private val remote: ProjectsDataSource
) : ProjectsDataSource {

    override fun getProjects(): Single<List<Project>> {
        return remote.getProjects()
    }

    override fun bookmarkProject(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun unbookmarkProject(projectId: String): Completable {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getBookmarkedProjects(): Single<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}