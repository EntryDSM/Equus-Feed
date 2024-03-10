package hs.kr.equus.feed.domain.notice.presentation.dto.request

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType

data class CreateNoticeRequest(
    val title: String?,
    val content: String?,
    val pick: Boolean?,
    val type: NoticeType?
)
