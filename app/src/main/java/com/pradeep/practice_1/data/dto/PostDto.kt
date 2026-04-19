package com.pradeep.practice_1.data.dto

import com.google.gson.annotations.SerializedName

data class PostDto(
    @SerializedName("userId") val userId: Long,
    @SerializedName("id") val id: Long,
    @SerializedName("title") val tile: String,
    @SerializedName("body") val body: String,
)