package hs.kr.equus.feed.domain.notice.presentation.dto.response

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import java.time.LocalDateTime
import java.util.*

data class NoticeResponse (
    val id: UUID,
    val title: String,
    val type: NoticeType,
    val isPinned: Boolean,
    val createAt: LocalDateTime
)
