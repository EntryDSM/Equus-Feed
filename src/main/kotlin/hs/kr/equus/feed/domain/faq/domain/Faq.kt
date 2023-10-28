package hs.kr.equus.feed.domain.faq.domain

import hs.kr.equus.feed.domain.BaseEntity
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
    var title: String,

    @Column(name = "content", length = 5000, nullable = false)
    var content: String,

    @Enumerated(EnumType.STRING)
    var faqType: FaqType,

    @Column(name = "admin_id", columnDefinition = "BINARY(16)", nullable = false)
    var adminId: UUID
) : BaseEntity(id) {
    fun updateFaq(title: String, content: String, faqType: FaqType, adminId: UUID) {
        this.title = title
        this.content = content
        this.faqType = faqType
        this.adminId = adminId
    }
}
