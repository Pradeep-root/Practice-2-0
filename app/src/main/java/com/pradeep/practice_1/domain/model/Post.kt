package com.pradeep.practice_1.domain.model

data class Post(
    val id: Long,
    val userId: Long,
    val title: String,
    val body: String
)