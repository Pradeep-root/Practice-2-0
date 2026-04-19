package com.pradeep.practice_1.presentation.posts

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pradeep.practice_1.core.common.AppResult
import com.pradeep.practice_1.core.common.toUserMessage
import com.pradeep.practice_1.core.dispatcher.DispatcherProvider
import com.pradeep.practice_1.domain.model.Post
import com.pradeep.practice_1.domain.usecase.GetPostUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PostViewModel @Inject constructor(
    private val getPostUseCase: GetPostUseCase,
    private val dispatcherProvider: DispatcherProvider
): ViewModel() {


    private val _uiState = MutableStateFlow(PostUiState())
    val uiState: StateFlow<PostUiState> = _uiState.asStateFlow()

    init {
        getPost()
    }

    fun getPost() {
        viewModelScope.launch(dispatcherProvider.main) {
            _uiState.update { it.copy(isLoading = true) }

            when (val result = getPostUseCase()) {

                is AppResult.Success -> _uiState.update {
                    it.copy(isLoading = false, posts = result.data)
                }
                is AppResult.Error -> _uiState.update {
                    it.copy(
                        isLoading = false,
                        userMsg = result.exception.toUserMessage()
                    )
                }
            }
        }
    }

}


data class PostUiState(
    val isLoading: Boolean = false,
    val posts: List<Post> = emptyList(),
    val userMsg: String? = null
)