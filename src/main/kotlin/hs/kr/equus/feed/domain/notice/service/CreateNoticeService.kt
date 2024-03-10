package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.Notice
import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.presentation.dto.request.CreateNoticeRequest
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.s3.service.S3Service
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class CreateNoticeService(
    private val s3Service: S3Service,
    private val noticeRepository: NoticeRepository,
    private val userUtils: UserUtils
) {

    @Transactional
    fun execute(
        image: List<MultipartFile>? = null,
        file: List<MultipartFile>? = null,
        request: CreateNoticeRequest
    ) {
        val user = userUtils.getCurrentUserId()

        val images = s3Service.upload(image!!)
        val files = s3Service.upload(file!!)

        val notice = Notice(
            adminId = user,
            title = request.title!!,
            content = request.content!!,
            type = request.type!!,
            pick = request.pick!!,
            imageName = images.toString(),
            fileName = files.toString()

        )

        noticeRepository.save(notice)
    }
}
