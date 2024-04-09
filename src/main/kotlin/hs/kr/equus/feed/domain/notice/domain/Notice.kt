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
    val title: String,

    @Column(name = "content", length = 5000, nullable = false)
    val content: String,

    @Column(name = "file_name")
    val fileName: String,

    @Column(name = "admin_name", nullable = false, columnDefinition = "BINARY(16)")
    val adminId: UUID,

    @Column(nullable = false)
    val isPinned: Boolean,

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    val type: NoticeType

) : BaseEntity(id)
