package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.presentation.dto.response.UploadNoticeImageResponse
import hs.kr.equus.feed.infrastructure.s3.util.notice.NoticeS3Util
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadNoticeImageService(
    private val noticeS3Util: NoticeS3Util
) {
    fun execute(image: MultipartFile): UploadNoticeImageResponse {
        val fileName = noticeS3Util.upload(image)
        return UploadNoticeImageResponse(noticeS3Util.generateObjectUrl(fileName), fileName)
    }
}
