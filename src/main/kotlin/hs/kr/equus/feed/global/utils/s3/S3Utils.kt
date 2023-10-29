package hs.kr.equus.feed.global.utils.s3

interface S3Utils {
    fun generateObjectUrl(fileName: String): String
}
