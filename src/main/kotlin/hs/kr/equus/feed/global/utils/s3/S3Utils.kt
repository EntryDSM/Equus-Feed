package hs.kr.equus.feed.global.utils.s3

import org.springframework.web.multipart.MultipartFile

interface S3Utils {

    fun upload(file : MultipartFile, path : String) : String

    fun generateObjectUrl(fileName: String): String
}
