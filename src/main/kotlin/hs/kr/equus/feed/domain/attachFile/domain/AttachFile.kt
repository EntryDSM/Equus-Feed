package hs.kr.equus.feed.domain.attachFile.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.UUID
import javax.persistence.Entity

@Entity(name = "tbl_attach_file")
class AttachFile(
    id: UUID? = null,
    var attachFile: String
) : BaseEntity(id) {
    fun modifyAttachFile(
        attachFile: String
    ) {
        this.attachFile = attachFile
    }
}
