package com.pradeep.practice_1.domain.usecase

import com.pradeep.practice_1.core.common.AppException
import com.pradeep.practice_1.core.common.AppResult
import com.pradeep.practice_1.domain.model.Post
import com.pradeep.practice_1.domain.repository.PostRepository
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

class GetPostUseCaseTest {


    private val mockPostRepository : PostRepository = mock()

    private lateinit var useCase: GetPostUseCase


    @Before
    fun setUp() {
        useCase = GetPostUseCase(mockPostRepository)
    }

    @Test
    fun test_invoke_repository_getPost_returns_success() = runTest {
        val expectedData = listOf(
            Post(id = 1, userId = 1, body = "body 1", title = "title 1")
        )

        `when`(mockPostRepository.getPosts())
            .thenReturn(AppResult.Success(expectedData))

        val result = useCase()

        assertTrue(result is AppResult.Success)
        assertEquals(expectedData, (result as AppResult.Success).data)
    }

    @Test
    fun test_inovke_repository_getPost_returns_error() = runTest {
        val networkError = AppResult.Error(AppException.Network("No connection"))

        `when`(mockPostRepository.getPosts())
            .thenReturn(networkError)

        val result = useCase()

        assertTrue(result is AppResult.Error)
        assertEquals(networkError, (result as AppResult.Error))
    }

    @Test
    fun test_invoke_repository_getPost_returns_empty() = runTest {
        `when`(mockPostRepository.getPosts())
            .thenReturn(AppResult.Success(emptyList()))

        val result = useCase()

        assertTrue(result is AppResult.Success)
        assertTrue((result as AppResult.Success).data.isEmpty())
    }

}