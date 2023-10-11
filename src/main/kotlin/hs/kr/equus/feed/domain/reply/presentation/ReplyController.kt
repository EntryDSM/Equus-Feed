package hs.kr.equus.feed.domain.reply.presentation

import hs.kr.equus.feed.domain.reply.presentation.dto.request.CreateReplyRequest
import hs.kr.equus.feed.domain.reply.presentation.dto.request.UpdateReplyRequest
import hs.kr.equus.feed.domain.reply.service.CreateReplyService
import hs.kr.equus.feed.domain.reply.service.UpdateReplyService
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/reply")
@RestController
class ReplyController(
    private val createReplyService: CreateReplyService,
    private val updateReplyService: UpdateReplyService
) {
    @PostMapping("/{questionId}")
    fun createReply(
        @RequestBody @Validated
        createReplyRequest: CreateReplyRequest,
        @PathVariable("questionId") questionId: UUID
    ) = createReplyService.execute(createReplyRequest, questionId)

    @PatchMapping("/{replyId}")
    fun updateReply(
        @RequestBody @Validated
        updateReplyRequest: UpdateReplyRequest,
        @PathVariable("replyId") replyId: UUID
    ) = updateReplyService.execute(updateReplyRequest, replyId)
}
