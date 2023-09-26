package hs.kr.equus.feed.domain.question.presentation.dto.response

import java.time.LocalDateTime
import java.util.UUID

data class QuestionDTO(
    val id: UUID,
    val title: String,
    val createdAt: LocalDateTime,
    val username: String,
    val isReplied: Boolean,
    val isPublic: Boolean
)
