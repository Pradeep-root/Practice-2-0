package com.pradeep.practice_1.data.repository

import com.pradeep.practice_1.core.common.AppException
import com.pradeep.practice_1.core.common.AppResult
import com.pradeep.practice_1.data.NetworkErrorMapper
import com.pradeep.practice_1.data.api.JsonPlaceHolderApi
import com.pradeep.practice_1.data.dto.PostDto
import com.pradeep.practice_1.data.mapper.toDomain
import com.pradeep.practice_1.domain.repository.PostRepository
import com.pradeep.practice_1.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import okio.IOException
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class PostRepositoryImplTest {


    private val mockApi: JsonPlaceHolderApi = mock()

    private val mockNetworkErrorMapper: NetworkErrorMapper = mock()

    private val testDispatcher = UnconfinedTestDispatcher()

    private val dispatcherProvider = TestDispatcherProvider(testDispatcher)

    private lateinit var postRepository: PostRepository


    @Before
    fun setUp() {
        postRepository = PostRepositoryImpl(
            mockApi,
            mockNetworkErrorMapper,
            dispatcherProvider
        )
    }

    @Test
    fun test_repository_getPost_returns_success() = runTest {
        val expectedData = listOf(
            PostDto(id = 1, userId = 1, body = "body 1", tile = "title 1")
        )

        `when`(mockApi.getPosts())
            .thenReturn(expectedData)

        val result = postRepository.getPosts()

        assertTrue(result is AppResult.Success)
        assertEquals(expectedData.map { it.toDomain() }, (result as AppResult.Success).data)

    }

    @Test
    fun test_repository_getPost_returns_error() = runTest {
        val error = AppResult.Error(AppException.Network("No connection"))

        `when`(mockApi.getPosts())
            .thenThrow(IOException("No connection"))

        `when`(mockNetworkErrorMapper.map(AppException.Network("No connection")))
            .thenReturn(AppException.Network("No connection"))

        val result = postRepository.getPosts()

        assertTrue(result is AppResult.Error)
        assertEquals(error, (result as AppResult.Error))
    }

    @Test
    fun test_repository_getPost_returns_emptyList() = runTest {
        `when`(mockApi.getPosts())
            .thenReturn(emptyList())

        val result = postRepository.getPosts()

        assertTrue(result is AppResult.Success)
        assertTrue((result as AppResult.Success).data.isEmpty())
    }

}