package hs.kr.equus.feed.domain.attachFile.domain

import hs.kr.equus.feed.domain.BaseEntity
import java.util.UUID
import javax.persistence.Entity

@Entity(name = "tbl_attach_file")
class AttachFile(
    id: UUID? = null,
    var attachFileName: String
) : BaseEntity(id) {
    fun modifyAttachFileName(
        attachFile: String
    ) {
        this.attachFileName = attachFile
    }
}
