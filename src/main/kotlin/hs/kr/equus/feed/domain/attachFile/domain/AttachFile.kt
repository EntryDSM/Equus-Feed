package hs.kr.equus.feed.domain.attachFile.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "tbl_attach_file")
class AttachFile(
    @Id
    val uploadedFilename: String,
    var originalAttachFileName: String
)
