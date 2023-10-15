package hs.kr.equus.feed.domain.reply.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.QuestionNotFoundException
import hs.kr.equus.feed.domain.reply.domain.Reply
import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.domain.reply.presentation.dto.request.CreateReplyRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.kafka.dto.CreateReplyEventRequest
import hs.kr.equus.feed.infrastructure.kafka.producer.reply.ReplyCreatedEventProducer
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class CreateReplyService(
    private val userUtils: UserUtils,
    private val replyRepository: ReplyRepository,
    private val questionRepository: QuestionRepository,
    private val replyCreatedEventProducer: ReplyCreatedEventProducer
) {
    @Transactional
    fun execute(createReplyRequest: CreateReplyRequest, questionId: UUID) {
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException
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
        replyCreatedEventProducer.send(CreateReplyEventRequest(questionId))
    }
}
