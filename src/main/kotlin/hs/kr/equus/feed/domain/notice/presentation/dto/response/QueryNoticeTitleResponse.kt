package hs.kr.equus.feed.domain.notice.presentation.dto.response

import java.time.LocalDate
import java.time.LocalDateTime
import java.util.UUID

data class QueryNoticeTitleResponse(
    val id: UUID,
    val title: String,
    val createAt: LocalDateTime
)
