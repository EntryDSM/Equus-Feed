package hs.kr.equus.feed.infrastructure.s3.util.notice

import org.springframework.web.multipart.MultipartFile

interface NoticeS3Util {
    fun upload(file: MultipartFile): String

    fun generateObjectUrl(fileName: String): String
}
