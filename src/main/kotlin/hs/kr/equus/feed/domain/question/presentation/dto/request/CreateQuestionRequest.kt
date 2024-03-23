package hs.kr.equus.feed.domain.question.presentation.dto.request

import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class CreateQuestionRequest(
    @field:NotBlank(message = "title은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 100, message = "title은 최대 100자까지 가능합니다.")
    val title: String,

    @field:NotBlank(message = "content은 null, 공백, 띄어쓰기를 허용하지 않습니다.")
    @field:Size(max = 5000, message = "content는 최대 5000자까지 가능합니다.")
    val content: String,

    @NotNull
    val isPublic: Boolean
)
