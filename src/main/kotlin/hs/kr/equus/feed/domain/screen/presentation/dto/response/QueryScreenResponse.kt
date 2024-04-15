package hs.kr.equus.feed.domain.screen.presentation.dto.response

import java.time.LocalDateTime
import java.util.UUID

data class QueryScreenResponse(
    val id: UUID,
    val image: String,
    val createAt: LocalDateTime,
    val modifyAt: LocalDateTime
)
