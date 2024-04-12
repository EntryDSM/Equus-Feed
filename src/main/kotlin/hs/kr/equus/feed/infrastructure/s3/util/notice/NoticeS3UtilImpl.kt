package hs.kr.equus.feed.infrastructure.s3.util.notice

import hs.kr.equus.feed.infrastructure.s3.util.S3Util
import org.springframework.stereotype.Component
import org.springframework.web.multipart.MultipartFile

@Component
class NoticeS3UtilImpl(
    private val s3Util: S3Util
) : NoticeS3Util {
    companion object {
        const val PATH = "notice/"
    }

    override fun upload(file: MultipartFile): String {
        return s3Util.upload(file, PATH)
    }

    override fun generateObjectUrl(fileName: String): String {
        return s3Util.generateObjectUrl(fileName, PATH)
    }
}
