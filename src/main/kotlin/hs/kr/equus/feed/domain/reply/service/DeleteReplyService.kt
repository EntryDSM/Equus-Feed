package hs.kr.equus.feed.domain.reply.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.QuestionNotFoundException
import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.domain.reply.exception.ReplyNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class DeleteReplyService(
    private val replyRepository: ReplyRepository,
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun execute(replyId: UUID) {
        val reply = replyRepository.findByIdOrNull(replyId) ?: throw ReplyNotFoundException
        questionRepository.findByIdOrNull(reply.questionId) ?.updateIsReplied(false) ?: throw QuestionNotFoundException
        replyRepository.delete(reply)
    }
}
