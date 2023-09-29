package com.rafiul.platzi2301.models.register

import com.google.gson.annotations.SerializedName

data class RequestRegister(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("avatar")
	val avatar: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
