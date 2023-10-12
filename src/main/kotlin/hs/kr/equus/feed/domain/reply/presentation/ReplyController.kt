package hs.kr.equus.feed.domain.reply.presentation

import hs.kr.equus.feed.domain.reply.presentation.dto.request.CreateReplyRequest
import hs.kr.equus.feed.domain.reply.presentation.dto.request.UpdateReplyRequest
import hs.kr.equus.feed.domain.reply.service.CreateReplyService
import hs.kr.equus.feed.domain.reply.service.DeleteReplyService
import hs.kr.equus.feed.domain.reply.service.UpdateReplyService
import org.springframework.http.HttpStatus
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RequestMapping("/reply")
@RestController
class ReplyController(
    private val createReplyService: CreateReplyService,
    private val updateReplyService: UpdateReplyService,
    private val deleteReplyService: DeleteReplyService
) {
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/{questionId}")
    fun createReply(
        @RequestBody @Validated
        createReplyRequest: CreateReplyRequest,
        @PathVariable("questionId") questionId: UUID
    ) = createReplyService.execute(createReplyRequest, questionId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PatchMapping("/{replyId}")
    fun updateReply(
        @RequestBody @Validated
        updateReplyRequest: UpdateReplyRequest,
        @PathVariable("replyId") replyId: UUID
    ) = updateReplyService.execute(updateReplyRequest, replyId)

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{replyId}")
    fun deleteReply(
        @PathVariable("replyId") replyId: UUID
    ) = deleteReplyService.execute(replyId)
}
