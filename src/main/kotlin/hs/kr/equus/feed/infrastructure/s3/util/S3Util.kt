package hs.kr.equus.feed.infrastructure.s3.util

import org.springframework.web.multipart.MultipartFile

interface S3Util {
    fun upload(file: MultipartFile, path: String): String

    fun generateObjectUrl(fileName: String, path: String): String
}
