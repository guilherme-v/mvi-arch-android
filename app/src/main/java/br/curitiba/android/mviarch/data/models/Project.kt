package br.curitiba.android.mviarch.data.models

data class Project(
    val id: String,
    val name: String,
    val fullName: String,
    val starCount: Int,
    val dateCreated: String,
    val ownerName: String,
    val ownerAvatar: String,
    val isBookmarked: Boolean
)