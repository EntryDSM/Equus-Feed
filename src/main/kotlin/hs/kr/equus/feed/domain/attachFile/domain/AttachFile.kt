package hs.kr.equus.feed.domain.attachFile.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "tbl_attach_file")
class AttachFile(
    @Id
    val id: String,
    var attachFileName: String,
)
