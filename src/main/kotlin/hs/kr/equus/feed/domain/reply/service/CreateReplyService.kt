package hs.kr.equus.feed.domain.reply.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.QuestionNotFoundException
import hs.kr.equus.feed.domain.reply.domain.Reply
import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.domain.reply.exception.ReplyExistsException
import hs.kr.equus.feed.domain.reply.presentation.dto.request.CreateReplyRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class CreateReplyService(
    private val userUtils: UserUtils,
    private val replyRepository: ReplyRepository,
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun execute(createReplyRequest: CreateReplyRequest, questionId: UUID) {
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException
        if (question.isReplied) {
            throw ReplyExistsException
        }
        val user = userUtils.getCurrentUser()

        createReplyRequest.run {
            replyRepository.save(
                Reply(
                    title = title,
                    content = content,
                    questionId = question.id!!,
                    adminId = user.id
                )
            )
        }
        question.updateIsReplied(true)
    }
}
