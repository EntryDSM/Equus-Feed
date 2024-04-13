package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.presentation.dto.response.UploadNoticeImageResponse
import hs.kr.equus.feed.infrastructure.s3.PathList
import hs.kr.equus.feed.infrastructure.s3.util.FileUtil
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadNoticeImageService(
    private val fileUtil: FileUtil
) {
    fun execute(image: MultipartFile): UploadNoticeImageResponse {
        val fileName = fileUtil.upload(image, PathList.NOTICE)
        return UploadNoticeImageResponse(fileUtil.generateObjectUrl(fileName, PathList.NOTICE), fileName)
    }
}
