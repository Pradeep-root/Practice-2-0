package com.pradeep.practice_1.data.mapper

import com.pradeep.practice_1.data.dto.PostDto
import com.pradeep.practice_1.domain.model.Post

fun PostDto.toDomain(): Post {
    return Post(
        id = id,
        userId = userId,
        title = tile.trim(),
        body = body.trim()
    )
}