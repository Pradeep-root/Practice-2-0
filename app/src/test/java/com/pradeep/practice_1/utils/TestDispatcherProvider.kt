package com.pradeep.practice_1.utils

import com.pradeep.practice_1.core.dispatcher.DispatcherProvider
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.test.UnconfinedTestDispatcher

class TestDispatcherProvider(
    private val testDispatcher: CoroutineDispatcher = UnconfinedTestDispatcher(),
): DispatcherProvider {
    override val main: CoroutineDispatcher
        get() = testDispatcher
    override val io: CoroutineDispatcher
        get() = testDispatcher
    override val default: CoroutineDispatcher
        get() = testDispatcher
}