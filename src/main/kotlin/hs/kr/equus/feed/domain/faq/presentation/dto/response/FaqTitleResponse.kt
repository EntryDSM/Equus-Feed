package hs.kr.equus.feed.domain.faq.presentation.dto.response

import java.time.LocalDateTime
import java.util.UUID

data class FaqTitleResponse(
    val id: UUID,
    val title: String,
    val content: String,
)
