package hs.kr.equus.feed.domain.screen.service

import hs.kr.equus.feed.domain.screen.domain.repository.ScreenRepository
import hs.kr.equus.feed.domain.screen.exception.ScreenNotFoundException
import hs.kr.equus.feed.domain.screen.presentation.dto.response.ScreenResponse
import hs.kr.equus.feed.infrastructure.s3.PathList
import hs.kr.equus.feed.infrastructure.s3.util.FileUtil
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.UUID

@Service
class UpdateScreenService(
    private val screenRepository: ScreenRepository,
    private val fileUtil: FileUtil
) {

    @Transactional
    fun execute(id: UUID, file: MultipartFile): ScreenResponse {
        val screen = screenRepository.findByIdOrNull(id)
            ?: throw ScreenNotFoundException
        val fileName = fileUtil.upload(file, PathList.SCREEN)
        fileUtil.delete(screen.image, PathList.SCREEN)
        screen.updateImage(fileName)

        return ScreenResponse(fileUtil.generateObjectUrl(fileName, PathList.SCREEN))
    }
}
