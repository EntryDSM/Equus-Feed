package hs.kr.equus.feed.domain.reply.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_reply")
class Reply(
    id: UUID?,

    @Column(name = "title", length = 150, nullable = false)
    val title: String,

    @Column(name = "content", length = 5000, nullable = false)
    val content: String,

    @Column(name = "question_id", nullable = false)
    val questionId: UUID,

    @Column(name = "admin_id", nullable = false)
    val adminId: UUID
) : BaseEntity(id)
