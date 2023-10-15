package hs.kr.equus.feed.domain.reply.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.domain.reply.exception.ReplyNotFoundException
import hs.kr.equus.feed.infrastructure.kafka.dto.DeleteReplyEventRequest
import hs.kr.equus.feed.infrastructure.kafka.producer.reply.ReplyDeletedEventProducer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteReplyService(
    private val replyRepository: ReplyRepository,
    private val replyDeletedEventProducer: ReplyDeletedEventProducer,
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun execute(replyId: UUID) {
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException
        replyDeletedEventProducer.send(DeleteReplyEventRequest(reply.questionId))
        replyRepository.delete(reply)
    }
}
