package hs.kr.equus.feed.domain.reply.service

import hs.kr.equus.feed.domain.reply.domain.repository.ReplyRepository
import hs.kr.equus.feed.domain.reply.presentation.dto.response.ReplyDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class GetReplyService(
    private val replyRepository: ReplyRepository
) {
    @Transactional
    fun execute(questionId: UUID): ReplyDto? {
        val reply = replyRepository.findByQuestionId(questionId)
        return reply?.let { it ->
            ReplyDto(
                title = it.title,
                content = it.content,
                createdAt = it.createdAt
            )
        }
    }
}
