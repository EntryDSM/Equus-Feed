package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.presentation.dto.response.UploadNoticeImageResponse
import hs.kr.equus.feed.infrastructure.s3.service.notice.NoticeS3Service
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadNoticeImageService(
    private val noticeS3Service: NoticeS3Service
) {
    fun execute(image: MultipartFile): UploadNoticeImageResponse {
        val fileName = noticeS3Service.upload(image)
        return UploadNoticeImageResponse(noticeS3Service.generateObjectUrl(fileName), fileName)
    }
}
