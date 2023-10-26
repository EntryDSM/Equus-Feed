package hs.kr.equus.feed.domain.faq.domain.repository

import hs.kr.equus.feed.domain.faq.domain.Faq
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface FaqRepository : JpaRepository<Faq, UUID>
