package br.curitiba.android.mviarch.data.source.remote.dto

import com.google.gson.annotations.SerializedName

// Do not trust API
// Allow receive null values here, but map it to Domain Not null val's with default values if needed
// This why we do not need to worry about null checks in any other layer ... if ( value != null ) { ... }

data class ProjectsResponseDTO(
    val items: List<ProjectDTO>? = null
)

data class ProjectDTO(
    val id: String? = null,
    val name: String? = null,
    @SerializedName("full_name") val fullName: String? = null,
    @SerializedName("stargazers_count") val starCount: Int? = null,
    @SerializedName("created_at") val dateCreated: String? = null,
    val owner: OwnerDTO? = null
)

data class OwnerDTO(
    @SerializedName("login") val ownerName: String? = null,
    @SerializedName("avatar_url") val ownerAvatar: String? = null
)

