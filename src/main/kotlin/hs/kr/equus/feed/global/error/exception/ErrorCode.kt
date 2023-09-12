package hs.kr.equus.user.global.error.exception

enum class ErrorCode(
    val status: Int,
    val code: String,
    val message: String
) {

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "COMMON500-0", "Internal Server Error"),
}
