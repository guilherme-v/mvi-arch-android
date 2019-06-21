package br.curitiba.android.mviarch.data.source.local

import br.curitiba.android.mviarch.Database
import br.curitiba.android.mviarch.data.models.Project
import br.curitiba.android.mviarch.data.source.ProjectsDataSource
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class LocalProjectsDataSource @Inject constructor(
    private val database: Database
) : ProjectsDataSource {

    override fun getProjects() = Single.just(
        database.projectEntityQueries.selectAll(::mapFromEntityToDomain).executeAsList()
    )

    override fun saveProjects(projects: List<Project>) = Completable.create { emitter ->
        try {
            projects.forEach {
                database.projectEntityQueries.insert(it.id, it.name, it.fullName, it.starCount, it.dateCreated,
                    it.ownerName, it.ownerAvatar, it.isBookmarked
                )
            }
        } catch (e: Exception) {
            emitter.onError(e)
        }
        emitter.onComplete()
    }

    private fun mapFromEntityToDomain(id: String, name: String, full_name: String, star_count: Int, date_created: String,
                                      owner_name: String, owner_avatar: String, is_bookmarked: Boolean): Project {
        return Project(id, name, full_name, star_count, date_created, owner_name, owner_avatar, is_bookmarked)
    }
}
