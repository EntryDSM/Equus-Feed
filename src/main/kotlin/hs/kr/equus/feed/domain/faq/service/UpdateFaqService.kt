package hs.kr.equus.feed.domain.faq.service

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.domain.faq.exception.FaqNotFoundException
import hs.kr.equus.feed.domain.faq.presentation.dto.request.UpdateFaqRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class UpdateFaqService(
    private val faqRepository: FaqRepository,
    private val userUtils: UserUtils
) {
    @Transactional
    fun execute(faqId: UUID, updateFaqRequest: UpdateFaqRequest) {
        val faq = faqRepository.findByIdOrNull(faqId) ?: throw FaqNotFoundException
        faq.updateFaq(
            title = updateFaqRequest.title,
            content = updateFaqRequest.content,
            faqType = updateFaqRequest.faqType,
            adminId = userUtils.getCurrentUserId()
        )
    }
}
