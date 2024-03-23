package hs.kr.equus.feed.domain.notice.presentation.dto.response

import java.util.UUID

data class QueryNoticeTitleResponse(
    val id: UUID,
    val title: String
)
