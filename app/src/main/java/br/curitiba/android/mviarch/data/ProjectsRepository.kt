package br.curitiba.android.mviarch.data

import br.curitiba.android.mviarch.data.models.Project
import io.reactivex.Completable
import io.reactivex.Single

interface ProjectsRepository {

    fun getProjects(): Single<List<Project>>

    fun setProjectAsBookmarked(projectId: String): Completable =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun setProjectAsNotBookmarked(projectId: String): Completable =
        throw UnsupportedOperationException("Operation isn't supported here...")
}