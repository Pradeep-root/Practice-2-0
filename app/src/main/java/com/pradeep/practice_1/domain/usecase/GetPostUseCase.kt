package com.pradeep.practice_1.domain.usecase

import com.pradeep.practice_1.core.common.AppResult
import com.pradeep.practice_1.domain.model.Post
import com.pradeep.practice_1.domain.repository.PostRepository
import javax.inject.Inject

class GetPostUseCase @Inject constructor(
    private val repository: PostRepository
) {

    suspend operator fun invoke(): AppResult<List<Post>> = repository.getPosts()
}