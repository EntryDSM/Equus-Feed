package hs.kr.equus.feed.domain.attachFile.service

import hs.kr.equus.feed.domain.attachFile.domain.AttachFile
import hs.kr.equus.feed.domain.attachFile.domain.repository.AttachFileRepository
import hs.kr.equus.feed.domain.attachFile.presentation.dto.response.CreateAttachFileResponse
import hs.kr.equus.feed.infrastructure.s3.PathList
import hs.kr.equus.feed.infrastructure.s3.util.FileUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class CreateAttachFileService(
    private val attachFileRepository: AttachFileRepository,
    private val fileUtil: FileUtil
) {
    fun execute(attachFile: MultipartFile): CreateAttachFileResponse {
        val attachFileUrl = fileUtil.upload(attachFile, PathList.ATTACH_FILE)
        attachFileRepository.save(
            AttachFile(
                attachFile = attachFileUrl
            )
        )
        return CreateAttachFileResponse(attachFileUrl, getUrl(attachFileUrl))
    }

    private fun getUrl(attachFileUrl: String) = fileUtil.generateObjectUrl(attachFileUrl, PathList.ATTACH_FILE)
}
