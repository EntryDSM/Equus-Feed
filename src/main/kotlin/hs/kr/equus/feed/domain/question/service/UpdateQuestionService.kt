package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.QuestionNotFoundException
import hs.kr.equus.feed.domain.question.presentation.dto.request.UpdateQuestionRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateQuestionService(
    private val questionRepository: QuestionRepository,
    private val userUtils: UserUtils
) {
    @Transactional
    fun execute(questionId: UUID, updateQuestionRequest: UpdateQuestionRequest) {
        val userId = userUtils.getCurrentUserId()
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException

        updateQuestionRequest.run {
            question.updateQuestion(
                userId = userId,
                title = title,
                content = content,
                isPublic = isPublic
            )
        }
    }
}
