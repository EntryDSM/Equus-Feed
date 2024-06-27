package hs.kr.equus.feed.domain.attachFile.presentation

import hs.kr.equus.feed.domain.attachFile.service.CreateAttachFileService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/attach-file")
class AttachFileController(
    private val createAttachFileService: CreateAttachFileService
) {
    @PostMapping
    fun createAttachFile(
        @RequestPart(value = "attach_file") attachFile: List<MultipartFile>
    ) = createAttachFileService.execute(attachFile)
}
