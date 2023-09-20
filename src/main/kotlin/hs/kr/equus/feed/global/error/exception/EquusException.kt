package hs.kr.equus.feed.global.error.exception

import java.lang.RuntimeException

abstract class EquusException(
    val errorCode: ErrorCode
) : RuntimeException()
