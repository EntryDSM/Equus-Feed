package hs.kr.equus.feed.domain.notice.presentation.dto.request

import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class ModifyNoticeRequest(
    @field:NotBlank(message = "title은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(max = 100, message = "title은 최대 100자까지 가능합니다.")
    val title: String,

    @field:NotBlank(message = "content은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @Size(max = 5000, message = "content은 최대 5000자까지 가능합니다.")
    val content: String,

    @field:NotNull(message = "Pinned은 null일수가 없습니다")
    val isPinned: Boolean,

    @field:NotNull(message = "type은 null일수가 없습니다")
    val type: NoticeType,

    val fileName: String
)
