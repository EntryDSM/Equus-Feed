package hs.kr.equus.feed.domain.notice.service

import hs.kr.equus.feed.domain.notice.domain.repository.NoticeRepository
import hs.kr.equus.feed.domain.notice.exception.NoticeNotFoundException
import hs.kr.equus.feed.domain.notice.presentation.dto.response.AttachFile
import hs.kr.equus.feed.domain.notice.presentation.dto.response.QueryDetailsNoticeResponse
import hs.kr.equus.feed.infrastructure.s3.PathList
import hs.kr.equus.feed.infrastructure.s3.util.FileUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Transactional(readOnly = true)
@Service
class QueryDetailsNoticeService(
    private val noticeRepository: NoticeRepository,
    private val fileUtil: FileUtil
) {
    fun execute(noticeId: UUID): QueryDetailsNoticeResponse {
        val notice = noticeRepository.findByIdOrNull(noticeId) ?: throw NoticeNotFoundException
        val imageURL = notice.fileName?.let { getUrl(it, PathList.NOTICE) }

        val attachFile =
            notice.attachFile?.map {
                // 첨부파일 이름과 url을 묶어서 여러개 반환하기때문에 List로 묶는다
                AttachFile(
                    attachFileUrl = getUrl(it.uploadedFileName, PathList.ATTACH_FILE),
                    attachFileName = it.originalAttachFileName
                )
            }

        return notice.run {
            QueryDetailsNoticeResponse(
                title = title,
                content = content,
                createdAt = createdAt,
                type = type,
                imageURL = imageURL,
                attachFiles = attachFile!!
            )
        }
    }

    private fun getUrl(file: String, path: String) = fileUtil.generateObjectUrl(file, path)
}
