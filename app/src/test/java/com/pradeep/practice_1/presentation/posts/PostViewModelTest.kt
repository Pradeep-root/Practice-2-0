package com.pradeep.practice_1.presentation.posts

import com.pradeep.practice_1.core.common.AppException
import com.pradeep.practice_1.core.common.AppResult
import com.pradeep.practice_1.core.common.toUserMessage
import com.pradeep.practice_1.core.dispatcher.DispatcherProvider
import com.pradeep.practice_1.domain.model.Post
import com.pradeep.practice_1.domain.usecase.GetPostUseCase
import com.pradeep.practice_1.utils.TestDispatcherProvider
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`

@OptIn(ExperimentalCoroutinesApi::class)
class PostViewModelTest {


    private val getPostUseCase : GetPostUseCase = mock()

    private val testUnconfinedDispatcher = UnconfinedTestDispatcher()

    private val testDispatcherProvider = TestDispatcherProvider(testUnconfinedDispatcher)


    private lateinit var viewModel: PostViewModel

    @Before
    fun setUp() {
        viewModel = PostViewModel(
            getPostUseCase,
            testDispatcherProvider
        )
    }

    @Test
    fun test_getPost_success_update_ui_state_correctly() = runTest {
        val mockPosts = listOf(
            Post(id = 1, userId = 1, body = "body 1", title = "title 1")
        )
        `when`(getPostUseCase())
            .thenReturn(AppResult.Success(mockPosts))

        // when
        viewModel.getPost()

        // then

        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(mockPosts, state.posts)
        assertNull(state.userMsg)
    }

    @Test
    fun test_getPost_error_update_ui_state_correctly() = runTest {

        val serverError = AppException.Sever(400, "Server respond with an error")
        `when`(getPostUseCase())
            .thenReturn(AppResult.Error(serverError))

        // when
        viewModel.getPost()

        // then
        val state = viewModel.uiState.value
        assertFalse(state.isLoading)
        assertEquals(serverError.toUserMessage(), state.userMsg)

    }


}