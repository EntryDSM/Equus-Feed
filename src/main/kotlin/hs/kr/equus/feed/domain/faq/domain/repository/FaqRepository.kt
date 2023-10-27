package hs.kr.equus.feed.domain.faq.domain.repository

import hs.kr.equus.feed.domain.faq.domain.Faq
import hs.kr.equus.feed.domain.faq.domain.type.FaqType
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FaqRepository : JpaRepository<Faq, UUID> {
    fun findAllByFaqType(faqType: FaqType) : List<Faq>
}
