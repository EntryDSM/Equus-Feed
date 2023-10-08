package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.Question
import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.AccessDeniedQuestionException
import hs.kr.equus.feed.domain.question.exception.QuestionNotFoundException
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionDetailsResponse
import hs.kr.equus.feed.global.security.jwt.UserRole
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.feign.client.user.UserFeignClient
import hs.kr.equus.feed.infrastructure.feign.client.user.model.User
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
class QueryQuestionDetailsService(
    private val questionRepository: QuestionRepository,
    private val userUtils: UserUtils,
    private val userFeignClient: UserFeignClient
) {
    @Transactional(readOnly = true)
    fun execute(questionId: UUID): QuestionDetailsResponse {
        val question = questionRepository.findByIdOrNull(questionId) ?: throw QuestionNotFoundException
        val user = userUtils.getCurrentUser()

        validateAccessPermission(question, user)

        return createResponse(question, user)
    }

    private fun validateAccessPermission(question: Question, user: User) {
        if (!question.isPublic) {
            if (question.userId != user.id) {
                throw AccessDeniedQuestionException
            }
            if (user.role.toString() != "ROLE_${UserRole.ADMIN}" && question.userId != user.id) {
                throw AccessDeniedQuestionException
            }
        }
    }

    private fun createResponse(question: Question, user: User): QuestionDetailsResponse {
        return QuestionDetailsResponse(
            id = question.id!!,
            title = question.title,
            content = question.content,
            username = userFeignClient.getUserByUUID(question.userId).name,
            isReplied = question.isReplied,
            isMine = (question.userId == user.id),
            isPublic = question.isPublic,
            createdAt = question.createdAt
            // Reply 개발 후 Response 에 추가합니다
        )
    }
}
