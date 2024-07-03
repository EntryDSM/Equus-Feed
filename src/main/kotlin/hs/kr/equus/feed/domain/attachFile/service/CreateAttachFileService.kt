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
    fun execute(attachFile: List<MultipartFile>): List<CreateAttachFileResponse> {
        val attachFileResponses = mutableListOf<CreateAttachFileResponse>()

        attachFile.forEach { file ->
            val uploadedFilename = fileUtil.upload(file, PathList.ATTACH_FILE)
            val attachFileEntity = AttachFile(
                uploadedFilename = uploadedFilename,
                originalAttachFileName = file.originalFilename!!
            )
            attachFileRepository.save(attachFileEntity)

            val url = fileUtil.generateObjectUrl(uploadedFilename, PathList.ATTACH_FILE)
            attachFileResponses.add(CreateAttachFileResponse(file.originalFilename!!, url))
        }
        return attachFileResponses
    }
}
