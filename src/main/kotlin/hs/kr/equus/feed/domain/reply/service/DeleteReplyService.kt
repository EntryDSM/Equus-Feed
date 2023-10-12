package hs.kr.equus.feed.domain.reply.service

import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.domain.reply.exception.ReplyNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteReplyService(
    private val replyRepository: ReplyRepository
) {
    @Transactional
    fun execute(replyId: UUID) {
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException
        replyRepository.delete(reply)
    }
}
