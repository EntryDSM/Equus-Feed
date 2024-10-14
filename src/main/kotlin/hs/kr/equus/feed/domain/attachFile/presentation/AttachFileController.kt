package hs.kr.equus.feed.domain.attachFile.presentation

import hs.kr.equus.feed.domain.attachFile.service.CreateAttachFileService
<<<<<<< HEAD
import org.springframework.web.bind.annotation.PostMapping
=======
import org.springframework.web.bind.annotation.PatchMapping
>>>>>>> 98df16cb9edf787a39b3dda32797004df8c808f3
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
