package br.curitiba.android.mviarch.data.repositories

import br.curitiba.android.mviarch.data.dto.Project
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsRepositoryImpl @Inject constructor() : ProjectsRepository {

    override fun getProjects(): Single<List<Project>> {
        return Single.just(
            listOf(
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains"),
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains"),
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains"),
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains"),
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains"),
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains"),
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains"),
                Project( id = "3432266", name = "kotlin", fullName = "JetBrains/kotlin", starCount = "28010", isBookmarked = false, ownerAvatar = "https://avatars2.githubusercontent.com/u/878437?v=4", ownerName = "JetBrains")
            )
        )
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