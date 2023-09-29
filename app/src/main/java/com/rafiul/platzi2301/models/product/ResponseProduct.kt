package com.rafiul.platzi2301.models.product

import com.google.gson.annotations.SerializedName
import com.rafiul.platzi2301.models.category.ResponseCategory

data class ResponseProduct(

	@field:SerializedName("images")
	val images: List<String?>? = null,

	@field:SerializedName("creationAt")
	val creationAt: String? = null,

	@field:SerializedName("price")
	val price: Int? = null,

	@field:SerializedName("description")
	val description: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("category")
	val category: ResponseCategory? = null,

	@field:SerializedName("updatedAt")
	val updatedAt: String? = null
)


