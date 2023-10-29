package hs.kr.equus.feed.domain.faq.service

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteFaqService(
    private val faqRepository: FaqRepository
) {
    @Transactional
    fun execute(faqId: UUID) = faqRepository.deleteById(faqId)
}
