package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.QuestionNotFoundException
import hs.kr.equus.feed.global.utils.user.UserUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteQuestionService(
    private val userUtils: UserUtils,
    private val questionRepository: QuestionRepository
) {
    @Transactional
    fun execute(questionId: UUID) {
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException
        val userId = userUtils.getCurrentUserId()

        question.validateWriter(userId)
        questionRepository.delete(question)
    }
}
