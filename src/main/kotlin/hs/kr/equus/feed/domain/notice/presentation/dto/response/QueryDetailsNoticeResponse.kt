package hs.kr.equus.feed.domain.notice.presentation.dto.response

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import java.time.LocalDateTime

data class QueryDetailsNoticeResponse(
    val title: String,
    val content: String,
    val createdAt: LocalDateTime,
    val type: NoticeType,
    val imageURL: String?,
    val attachFiles: List<AttachFile> = emptyList()
)

data class AttachFile(
    val attachFileUrl: String,
    val attachFileName: String
)
