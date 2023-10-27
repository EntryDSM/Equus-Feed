package hs.kr.equus.feed.domain.faq.presentation.dto.response

import hs.kr.equus.feed.domain.faq.domain.type.FaqType
import java.time.LocalDate
import java.time.LocalDateTime

data class FaqDetailsResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val faqType: FaqType
)
