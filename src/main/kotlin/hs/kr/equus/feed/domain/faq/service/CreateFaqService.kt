package hs.kr.equus.feed.domain.faq.service

import hs.kr.equus.feed.domain.faq.domain.Faq
import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.domain.faq.presentation.dto.request.CreateFaqRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CreateFaqService(
    private val faqRepository: FaqRepository,
    private val userUtils: UserUtils
) {
    @Transactional
    fun execute(createFaqRequest: CreateFaqRequest){
        val faq = Faq(
            title = createFaqRequest.title,
            content = createFaqRequest.content,
            faqType = createFaqRequest.faqType,
            adminId = userUtils.getCurrentUserId()
        )
        faqRepository.save(faq);
    }
}
