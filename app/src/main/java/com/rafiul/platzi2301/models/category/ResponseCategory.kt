package com.rafiul.platzi2301.models.category

import com.google.gson.annotations.SerializedName

data class ResponseCategory(

@field:SerializedName("image")
val image: String? = null,

@field:SerializedName("creationAt")
val creationAt: String? = null,

@field:SerializedName("name")
val name: String? = null,

@field:SerializedName("id")
val id: Int? = null,

@field:SerializedName("updatedAt")
val updatedAt: String? = null
)

