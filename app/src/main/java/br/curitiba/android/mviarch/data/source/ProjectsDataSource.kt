package br.curitiba.android.mviarch.data.source

import br.curitiba.android.mviarch.data.models.Project
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

interface ProjectsDataSource {

    fun getProjects(): Single<List<Project>> =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun saveProjects(projects: List<Project>): Completable =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun clearProjects(): Completable  =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun getBookmarkedProjects(): Single<List<Project>> =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun setProjectAsBookmarked(projectId: String): Completable =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun setProjectAsNotBookmarked(projectId: String): Completable =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun areProjectsCached(): Single<Boolean> =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun setLastCacheTime(lastCache: Long): Completable =
        throw UnsupportedOperationException("Operation isn't supported here...")

    fun isProjectsCacheExpired(): Single<Boolean> =
        throw UnsupportedOperationException("Operation isn't supported here...")
}