package br.curitiba.android.mviarch.data.source

import br.curitiba.android.mviarch.data.models.Project
import io.reactivex.Completable
import io.reactivex.Single

interface ProjectsDataSource {

    fun getProjects(): Single<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getBookmarkedProjects(): Single<List<Project>>
}