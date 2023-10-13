package hs.kr.equus.feed.domain.question.presentation.dto.response

import hs.kr.equus.feed.domain.reply.presentation.dto.response.ReplyDto
import java.time.LocalDateTime
import java.util.UUID

data class QuestionDetailsResponse(
    val id: UUID,
    val title: String,
    val content: String,
    val username: String,
    val isReplied: Boolean,
    val isMine: Boolean,
    val isPublic: Boolean,
    val createdAt: LocalDateTime,
    val reply: ReplyDto?
)
