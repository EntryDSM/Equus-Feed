package hs.kr.equus.feed.infrastructure.s3.service.notice

import org.springframework.web.multipart.MultipartFile

interface NoticeS3Service {
    fun upload(file: MultipartFile): String

    fun generateObjectUrl(fileName: String): String
}
