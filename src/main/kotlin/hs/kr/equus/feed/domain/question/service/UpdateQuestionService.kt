package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.FeedWriterMismatchException
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
        val user = userUtils.getCurrentUser()
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException

        if (question.userId != user.id) {
            throw FeedWriterMismatchException
        }

        question.updateQuestion(
            title = updateQuestionRequest.title,
            content = updateQuestionRequest.content,
            isPublic = updateQuestionRequest.isPublic
        )
    }
}
