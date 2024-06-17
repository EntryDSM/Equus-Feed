package hs.kr.equus.feed.domain.attachFile.domain

import hs.kr.equus.feed.domain.BaseEntity
import hs.kr.equus.feed.domain.notice.domain.Notice
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

@Entity(name = "tbl_attach_file")
class AttachFile (
    id: UUID? = null,
    val attachFile: String,
): BaseEntity(id)
