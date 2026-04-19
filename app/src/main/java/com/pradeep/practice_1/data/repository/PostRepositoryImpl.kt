package com.pradeep.practice_1.data.repository

import com.pradeep.practice_1.core.common.AppResult
import com.pradeep.practice_1.core.dispatcher.DispatcherProvider
import com.pradeep.practice_1.data.NetworkErrorMapper
import com.pradeep.practice_1.data.api.JsonPlaceHolderApi
import com.pradeep.practice_1.data.mapper.toDomain
import com.pradeep.practice_1.domain.model.Post
import com.pradeep.practice_1.domain.repository.PostRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostRepositoryImpl @Inject constructor(
    private val api: JsonPlaceHolderApi,
    private val errorMapper: NetworkErrorMapper,
    private val dispatcherProvider: DispatcherProvider
): PostRepository {

    override suspend fun getPosts(): AppResult<List<Post>> = withContext(dispatcherProvider.io) {
        try {
            val response = api.getPosts()
            AppResult.Success(response.map { it.toDomain() })
        }catch (e: Exception) {
            AppResult.Error(errorMapper.map(e))
        }
    }

}