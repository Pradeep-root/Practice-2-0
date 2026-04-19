package com.pradeep.practice_1.data.api

import com.pradeep.practice_1.data.dto.PostDto
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("posts")
    suspend fun getPosts(): List<PostDto>
}