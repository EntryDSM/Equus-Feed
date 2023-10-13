package hs.kr.equus.feed.domain.reply.presentation.dto.response

import java.time.LocalDateTime

data class ReplyDto(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime
)
