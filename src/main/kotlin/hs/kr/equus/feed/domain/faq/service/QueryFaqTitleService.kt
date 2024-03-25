package hs.kr.equus.feed.domain.faq.service

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.domain.faq.presentation.dto.response.FaqTitleResponse
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryFaqTitleService(
    private val faqRepository: FaqRepository
) {

    @Transactional(readOnly = true)
    fun execute(): List<FaqTitleResponse> =
        faqRepository.findAll()
            .sortedByDescending { it.createdAt }
            .map {
                FaqTitleResponse(
                    it.id!!,
                    it.title,
                    it.content
                )
            }
}
