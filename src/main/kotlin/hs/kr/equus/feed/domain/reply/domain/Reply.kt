package hs.kr.equus.feed.domain.reply.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity

@Entity(name = "tbl_reply")
class Reply(
    id: UUID? = null,

    @Column(name = "title", length = 150, nullable = false)
    var title: String,

    @Column(name = "content", length = 5000, nullable = false)
    var content: String,

    @Column(name = "question_id", nullable = false)
    val questionId: UUID,

    @Column(name = "admin_id", nullable = false)
    val adminId: UUID
) : BaseEntity(id) {
    fun updateReply(title: String, content: String){
        this.title = title
        this.content = content
    }
}
