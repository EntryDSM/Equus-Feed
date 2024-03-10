package hs.kr.equus.feed.infrastructure.s3.service

import com.amazonaws.HttpMethod
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.model.CannedAccessControlList
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest
import com.amazonaws.services.s3.model.ObjectMetadata
import com.amazonaws.services.s3.model.PutObjectRequest
import hs.kr.equus.feed.infrastructure.s3.exception.BadFileExtensionException
import hs.kr.equus.feed.infrastructure.s3.exception.EmptyFileException
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.io.ByteArrayInputStream
import java.io.InputStream
import java.util.Date
import java.util.Locale
import java.util.UUID

@Service
class S3Service(
    private val amazonS3: AmazonS3
) {
    @Value("\${cloud.aws.s3.bucket}")
    lateinit var bucketName: String

    companion object {
        const val EXP_TIME = 1000 * 60 * 2
    }

    fun upload(files: List<MultipartFile>, path: String): List<String> {
        val uploadedFileNames = mutableListOf<String>()

        files.forEach { file ->
            val ext = verificationFile(file)

            val randomName = UUID.randomUUID().toString()
            val filename = "$randomName.$ext"
            val inputStream: InputStream = ByteArrayInputStream(file.bytes)

            val metadata = ObjectMetadata().apply {
                contentType = MediaType.IMAGE_PNG_VALUE
                contentLength = file.size
                contentDisposition = "inline"
            }

            inputStream.use {
                amazonS3.putObject(
                    PutObjectRequest(bucketName, "${path}$filename", it, metadata)
                        .withCannedAcl(CannedAccessControlList.AuthenticatedRead)
                )
            }

            uploadedFileNames.add(filename)
        }

        return uploadedFileNames
    }

    fun generateObjectUrl(fileNames: List<String>, path: String): List<String> {
        val expiration = Date().apply {
            time += EXP_TIME
        }

        val url = mutableListOf<String>()

        fileNames.forEach { fileName ->
            val urls = amazonS3.generatePresignedUrl(
                GeneratePresignedUrlRequest(
                    bucketName,
                    "${path}$fileName"
                ).withMethod(HttpMethod.GET).withExpiration(expiration)
            ).toString()

            url.add(urls)
        }

        return url
    }

    private fun verificationFile(file: MultipartFile): String {
        if (file.isEmpty || file.originalFilename == null) throw EmptyFileException
        val originalFilename = file.originalFilename!!
        val ext = originalFilename.substring(originalFilename.lastIndexOf(".") + 1).lowercase(Locale.getDefault())

        if (!(ext == "jpg" || ext == "jpeg" || ext == "png" || ext == "heic" || ext == "hwp" || ext == "pptx")) {
            throw BadFileExtensionException
        }

        return ext
    }
}
