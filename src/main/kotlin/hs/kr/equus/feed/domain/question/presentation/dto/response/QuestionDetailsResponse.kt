package hs.kr.equus.feed.domain.question.presentation.dto.response

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
    val createdAt: LocalDateTime
    // Reply는 추후 Reply 개발 후 추가할 계획입니다
)
