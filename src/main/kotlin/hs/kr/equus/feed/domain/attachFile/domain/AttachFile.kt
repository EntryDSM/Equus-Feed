package hs.kr.equus.feed.domain.attachFile.domain

import javax.persistence.Entity
import javax.persistence.Id

@Entity(name = "tbl_attach_file")
class AttachFile(
    @Id
    val uploadedFilename: String, //aws s3에 올라가는 fileName
    var originalAttachFileName: String // 인코딩 되기 전 첨부파일 이름 ex): 서프수행.hwp
)
