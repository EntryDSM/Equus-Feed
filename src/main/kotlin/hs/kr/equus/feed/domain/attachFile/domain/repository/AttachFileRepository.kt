package hs.kr.equus.feed.domain.attachFile.domain.repository

import hs.kr.equus.feed.domain.attachFile.domain.AttachFile
import org.springframework.data.jpa.repository.JpaRepository

interface AttachFileRepository : JpaRepository<AttachFile, String> {
    fun findByOriginalAttachFileName(attachFileName: String): List<AttachFile>?

    fun deleteByOriginalAttachFileName(attachFileName: String)

    fun existsByOriginalAttachFileName(attachFileName: String): Boolean
}
