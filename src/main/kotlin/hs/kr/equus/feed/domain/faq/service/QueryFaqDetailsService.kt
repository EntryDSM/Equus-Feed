package hs.kr.equus.feed.domain.faq.service

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.domain.faq.exception.FaqNotFoundException
import hs.kr.equus.feed.domain.faq.presentation.dto.response.FaqDetailsResponse
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class QueryFaqDetailsService(
    private val faqRepository: FaqRepository
) {
    @Transactional
    fun execute(faqId: UUID) : FaqDetailsResponse{
        val faq = faqRepository.findByIdOrNull(faqId) ?: throw FaqNotFoundException
        return FaqDetailsResponse(
            title = faq.title,
            content = faq.content,
            createdAt = faq.createdAt,
            faqType =  faq.faqType
        )
    }
}
