package hs.kr.equus.feed.infrastructure.s3.service.notice

import hs.kr.equus.feed.infrastructure.s3.service.S3Service
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class NoticeS3ServiceImpl(
    private val s3Service: S3Service
) : NoticeS3Service {
    companion object {
        const val PATH = "notice/"
    }

    override fun upload(file: MultipartFile): String {
        return s3Service.upload(file, PATH)
    }

    override fun generateObjectUrl(fileName: String): String {
        return s3Service.generateObjectUrl(fileName, PATH)
    }
}
