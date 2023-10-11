package hs.kr.equus.feed.domain.reply.presentation

import hs.kr.equus.feed.domain.reply.presentation.dto.request.CreateReplyRequest
import hs.kr.equus.feed.domain.reply.service.CreateReplyService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID
import javax.validation.Valid

@RequestMapping("/reply")
@RestController
class ReplyController(
    private val createReplyService: CreateReplyService
) {
    @PostMapping("/{questionId}")
    fun createReply(
        @RequestBody @Valid
        createReplyRequest: CreateReplyRequest,
        @PathVariable("questionId") questionId: UUID
    ) = createReplyService.execute(createReplyRequest, questionId)
}