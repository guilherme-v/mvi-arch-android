package br.curitiba.android.mviarch.data.source.remote

import br.curitiba.android.mviarch.data.models.Project
import br.curitiba.android.mviarch.data.source.ProjectsDataSource
import br.curitiba.android.mviarch.data.source.remote.dto.ProjectDTO
import br.curitiba.android.mviarch.data.source.remote.service.GithubTrendingService
import io.reactivex.Single
import javax.inject.Inject

class RemoteProjectsDataSource @Inject constructor(
    private val service: GithubTrendingService
) : ProjectsDataSource {

    override fun getProjects(): Single<List<Project>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .flatMapIterable { it.items }
            .filter { it.id != null }
            .map { dto -> mapFromDTOtoDomain(dto) }
            .toList()
    }

    override fun getBookmarkedProjects(): Single<List<Project>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private fun mapFromDTOtoDomain(dto: ProjectDTO): Project = with(dto) {
        Project(id!!, name ?: "", fullName ?: "", starCount ?: -1, dateCreated ?: "", owner?.ownerName ?: "",
            owner?.ownerAvatar ?: "", isBookmarked = false)
    }
}