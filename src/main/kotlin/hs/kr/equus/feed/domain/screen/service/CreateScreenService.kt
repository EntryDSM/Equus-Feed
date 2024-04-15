package hs.kr.equus.feed.domain.screen.service

import hs.kr.equus.feed.domain.screen.domain.Screen
import hs.kr.equus.feed.domain.screen.domain.repository.ScreenRepository
import hs.kr.equus.feed.domain.screen.presentation.dto.response.ScreenResponse
import hs.kr.equus.feed.global.utils.user.UserUtils
import hs.kr.equus.feed.infrastructure.s3.PathList
import hs.kr.equus.feed.infrastructure.s3.util.FileUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile

@Service
class CreateScreenService(
    private val screenRepository: ScreenRepository,
    private val fileUtil: FileUtil,
    private val userUtils: UserUtils
) {

    @Transactional
    fun execute(file: MultipartFile): ScreenResponse {
        val adminId = userUtils.getCurrentUser().id

        val fileName = fileUtil.upload(file, PathList.NOTICE)
        screenRepository.save(
            Screen(
                image = fileName,
                adminId =  adminId
            )
        )
        return ScreenResponse(fileName)
    }
}
