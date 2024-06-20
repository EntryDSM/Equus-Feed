package hs.kr.equus.feed.domain.notice.presentation.dto.response

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import java.time.LocalDateTime

data class GetNoticeResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val type: NoticeType,
    val imageURL: String?,
    val attachFileUrl: List<String>? = emptyList()
)
