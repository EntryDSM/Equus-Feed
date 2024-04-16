package hs.kr.equus.feed.domain.screen.service

import hs.kr.equus.feed.domain.screen.domain.repository.ScreenRepository
import hs.kr.equus.feed.domain.screen.presentation.dto.response.QueryScreenResponse
import hs.kr.equus.feed.infrastructure.s3.PathList
import hs.kr.equus.feed.infrastructure.s3.util.FileUtil
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class QueryScreenService(
    private val screenRepository: ScreenRepository,
    private val fileUtil: FileUtil
) {
    @Transactional(readOnly = true)
    fun execute(): List<QueryScreenResponse> =
        screenRepository.findAll()
            .map { it ->
                QueryScreenResponse(
                    it.id!!,
                    fileUtil.generateObjectUrl(it.image, PathList.SCREEN),
                    it.createdAt,
                    it.modifiedAt
                )
            }
}
