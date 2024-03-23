package hs.kr.equus.feed.domain.faq.presentation.dto.response

import hs.kr.equus.feed.domain.faq.domain.type.FaqType
import java.time.LocalDateTime
import java.util.UUID

data class FaqDto(
    val id: UUID,
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val faqType: FaqType
)
