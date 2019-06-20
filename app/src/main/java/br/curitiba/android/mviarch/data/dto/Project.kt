package br.curitiba.android.mviarch.data.dto

data class Project(
    val id: String? = null,
    val name: String? = null,
    val fullName: String? = null,
    val starCount: String? = null,
    val dateCreated: String? = null,
    val ownerName: String? = null,
    val ownerAvatar: String? = null,
    val isBookmarked: Boolean? = null
)