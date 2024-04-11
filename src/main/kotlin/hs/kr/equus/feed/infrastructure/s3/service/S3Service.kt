package hs.kr.equus.feed.infrastructure.s3.service

import org.springframework.web.multipart.MultipartFile

interface S3Service {
    fun upload(file: MultipartFile, path: String): String

    fun generateObjectUrl(fileName: String, path: String): String
}
