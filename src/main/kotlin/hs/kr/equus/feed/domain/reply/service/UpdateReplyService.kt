package hs.kr.equus.feed.domain.reply.service

import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.domain.reply.exception.ReplyNotFoundException
import hs.kr.equus.feed.domain.reply.presentation.dto.request.UpdateReplyRequest
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateReplyService(
    private val replyRepository: ReplyRepository
) {
    @Transactional
    fun execute(updateReplyRequest: UpdateReplyRequest, replyId: UUID) {
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException

        updateReplyRequest.run {
            reply.updateReply(
                title = title,
                content = content
            )
        }
    }
}
