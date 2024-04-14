package hs.kr.equus.feed.domain.notice.presentation.dto.response

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import java.time.LocalDateTime
import java.util.UUID

data class QueryNoticeResponse(
    val notices: List<NoticeResponse>
)
