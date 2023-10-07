package hs.kr.equus.feed.domain.question.service

import hs.kr.equus.feed.domain.question.domain.Question
import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.domain.question.exception.AccessDeniedQuestionException
import hs.kr.equus.feed.domain.question.exception.QuestionNotFoundException
import hs.kr.equus.feed.domain.question.presentation.dto.response.QuestionDetailsResponse
import hs.kr.equus.feed.global.security.jwt.UserRole
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.global.utils.user.dto.UserInfo
import hs.kr.equus.feed.infrastructure.feign.client.user.UserFeignClient
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
        val userInfo = userUtils.getCurrentUserIdAndRole()

        validateAccessPermission(question, userInfo)

        return createResponse(question, userInfo)
    }

    private fun validateAccessPermission(question: Question, userInfo: UserInfo) {
        if (!question.isPublic && userInfo.userId != question.userId ) {
            throw AccessDeniedQuestionException
        }
        if(!question.isPublic && userInfo.userRole != "ROLE_${UserRole.ADMIN}" && question.userId != userInfo.userId) {
            throw AccessDeniedQuestionException
        }
    }

    private fun createResponse(question: Question, userInfo: UserInfo): QuestionDetailsResponse {
        return QuestionDetailsResponse(
            id = question.id!!,
            title = question.title,
            content = question.content,
            username = userFeignClient.getUserByUUID(question.userId).name,
            isReplied = question.isReplied,
            isMine = (question.userId == userInfo.userId),
            isPublic = question.isPublic,
            createdAt = question.createdAt
            // Reply 개발 후 Response 에 추가합니다
        )
    }
}
