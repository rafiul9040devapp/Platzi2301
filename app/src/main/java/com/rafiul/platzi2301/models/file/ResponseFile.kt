package com.rafiul.platzi2301.models.file


import com.google.gson.annotations.SerializedName

data class ResponseFile(
    @SerializedName("filename")
    var filename: String?,
    @SerializedName("location")
    var location: String?,
    @SerializedName("originalname")
    var originalname: String?
)