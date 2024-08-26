package hs.kr.equus.feed.domain.attachFile.domain.repository

import hs.kr.equus.feed.domain.attachFile.domain.AttachFile
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface AttachFileRepository : JpaRepository<AttachFile, String> {
    fun findByOriginalAttachFileName(attachFileName: String): List<AttachFile>?
}
