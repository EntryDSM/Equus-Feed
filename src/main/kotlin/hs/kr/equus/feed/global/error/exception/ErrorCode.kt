package hs.kr.equus.feed.global.error.exception

enum class ErrorCode(
    val status: Int,
    val message: String
) {
    // Feign
    FEIGN_BAD_REQUEST(400, "Feign Bad Request"),
    FEIGN_UNAUTHORIZED(401, "Feign UnAuthorized"),
    FEIGN_FORBIDDEN(403, "Feign Forbidden"),
    FEIGN_SERVER_ERROR(500, "Feign Server Error"),

    // Internal Server Error
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),

    // UnAuthorization
    INVALID_TOKEN(401, "Invalid Token"),
    EXPIRED_TOKEN(401, "Expired Token"),

    // Forbidden
    ACCESS_DENIED_QUESTION(403, "No Permission To Access Question"),
    ACCESS_DENIED_REPLY(403, "No Permission To Comment Question"),
    FEED_WRITER_MISMATCH(403, "Feed Writer Mismatch"),

    // Not Found
    QUESTION_NOT_FOUND(404, "Question Not Found"),
    REPLY_NOT_FOUND(404, "Reply Not Found"),
    FAQ_NOT_FOUND(404, "Faq Not Found"),
    NOTICE_NOT_FOUND(404, "Notice Not Found"),
    SCREEN_NOT_FOUND(404, "Screen Not Found"),
    ATTACH_FILE_NOT_FOUND(404, "Attach Not Found"),

    // Bad Request
    FILE_IS_EMPTY(400, "File does not exist"),
    BAD_FILE_EXTENSION(400, "File Extension is invalid"),

    // Conflict
    REPLY_EXISTS(409, "Reply Already Exists")
}
