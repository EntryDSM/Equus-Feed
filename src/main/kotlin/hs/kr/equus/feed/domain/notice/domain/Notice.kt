package hs.kr.equus.feed.domain.notice.domain

import hs.kr.equus.feed.domain.BaseEntity
import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import java.util.*
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity(name = "tbl_notice")
class Notice(
    id: UUID? = null,

    @Column(name = "title", length = 100, nullable = false)
    var title: String,

    @Column(name = "content", length = 5000, nullable = false)
    var content: String,

    @Column(name = "file_name", nullable = true)
    var fileName: String? = null,

    @Column(name = "admin_name", nullable = false, columnDefinition = "BINARY(16)")
    var adminId: UUID,

    @Column(nullable = false)
    var isPinned: Boolean,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    var type: NoticeType

) : BaseEntity(id) {
    fun modifyNotice(
        title: String,
        content: String,
        isPinned: Boolean,
        adminId: UUID,
        fileName: String,
        type: NoticeType
    ) {
        this.title = title
        this.content = content
        this.type = type
        this.isPinned = isPinned
        this.adminId = adminId
        this.fileName = fileName
    }
}
