package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.presentation.dto.response.UploadNoticeImageResponse
import hs.kr.equus.feed.infrastructure.s3.service.S3Service
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

@Service
class UploadNoticeImageService(
    private val s3Service: S3Service,
) {
   fun execute(image: MultipartFile): UploadNoticeImageResponse {
       val fileName = s3Service.upload(image, "notice/")
       return UploadNoticeImageResponse(s3Service.generateObjectUrl(fileName), fileName)
   }
}
