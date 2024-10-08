package hs.kr.equus.feed.domain.notice.domain

import hs.kr.equus.feed.domain.BaseEntity
import hs.kr.equus.feed.domain.attachFile.domain.AttachFile
import hs.kr.equus.feed.domain.notice.domain.type.NoticeType
import java.util.*
import javax.persistence.*

@Entity(name = "tbl_notice")
class Notice(
    id: UUID? = null,

    @Column(name = "title", length = 100, nullable = false)
    var title: String,

    @Column(name = "content", length = 5000, nullable = false)
    var content: String,

    @Column(name = "file_name", nullable = true)
    var fileName: String? = null,

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "noticeId")
    var attachFile: List<AttachFile>? = emptyList(),

    @Column(name = "admin_id", nullable = false, columnDefinition = "BINARY(16)")
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
        fileName: String?,
        type: NoticeType,
        attachFile: List<AttachFile>?
    ) {
        this.title = title
        this.content = content
        this.type = type
        this.isPinned = isPinned
        this.adminId = adminId
        this.fileName = fileName
        this.attachFile = attachFile
    }
}
