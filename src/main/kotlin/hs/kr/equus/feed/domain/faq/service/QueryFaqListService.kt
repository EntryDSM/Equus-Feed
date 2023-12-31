package hs.kr.equus.feed.domain.faq.service

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.domain.faq.presentation.dto.response.FaqDto
import hs.kr.equus.feed.domain.faq.presentation.dto.response.FaqListResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryFaqListService(
    private val faqRepository: FaqRepository
) {
    @Transactional
    fun execute(): FaqListResponse {
        val faqs = faqRepository.findAll().map {
            FaqDto(
                id = it.id!!,
                title = it.title,
                content = it.content,
                createdAt = it.createdAt,
                faqType = it.faqType
            )
        }
        return FaqListResponse(faqs)
    }
}
