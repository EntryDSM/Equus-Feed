package hs.kr.equus.feed.domain.faq.domain

import hs.kr.equus.feed.domain.BaseEntity
import hs.kr.equus.feed.domain.BaseTimeEntity
import hs.kr.equus.feed.domain.faq.domain.type.FaqType
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "tbl_faq")
class Faq(
    id: UUID? = null,

    @Column(name = "title", length = 100, nullable = false)
    val title: String,

    @Column(name = "content", length = 5000, nullable = false)
    val content: String,

    @Enumerated(EnumType.STRING)
    val faqType: FaqType,

    @Column(name = "admin_id", columnDefinition = "BINARY(16)", nullable = false)
    val adminId: UUID
) : BaseEntity(id)
