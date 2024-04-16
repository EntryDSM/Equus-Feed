package hs.kr.equus.feed.domain.screen.exception

import hs.kr.equus.feed.global.error.exception.EquusException
import hs.kr.equus.feed.global.error.exception.ErrorCode

object ScreenNotFoundException : EquusException(
    ErrorCode.SCREEN_NOT_FOUND
)
