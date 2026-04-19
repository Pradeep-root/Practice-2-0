package com.pradeep.practice_1.domain.repository

import com.pradeep.practice_1.core.common.AppResult
import com.pradeep.practice_1.domain.model.Post

interface PostRepository {

    suspend fun getPosts(): AppResult<List<Post>>
}