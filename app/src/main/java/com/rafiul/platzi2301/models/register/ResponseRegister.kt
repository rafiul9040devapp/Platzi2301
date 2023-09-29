package com.rafiul.platzi2301.models.register

import com.google.gson.annotations.SerializedName

data class ResponseRegister(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("role")
	val role: String? = null,

	@field:SerializedName("creationAt")
	val creationAt: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)
