package hs.kr.equus.feed.domain.reply.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

data class CreateReplyRequest(
    @field:NotBlank(message = "{title.notBlank}")
    @field:Size(max = 100, message = "{title.size}")
    val title: String,

    @field:NotBlank(message = "{content.notBlank}")
    @field:Size(max = 5000, message = "{content.size}")
    val content: String
)
