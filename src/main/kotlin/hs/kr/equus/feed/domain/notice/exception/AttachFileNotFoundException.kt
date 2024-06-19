package hs.kr.equus.feed.domain.notice.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object AttachFileNotFoundException : EquusException(
    ErrorCode.ATTACH_FILE_NOT_FOUND
)
