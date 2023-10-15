package hs.kr.equus.feed.infrastructure.kafka.producer.reply

import hs.kr.equus.feed.infrastructure.kafka.dto.DeleteReplyEventRequest

interface ReplyDeletedEventProducer {
    fun send(deleteReplyEventRequest: DeleteReplyEventRequest)
}
