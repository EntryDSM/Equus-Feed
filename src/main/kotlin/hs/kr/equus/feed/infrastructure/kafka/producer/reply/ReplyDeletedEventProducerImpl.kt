package hs.kr.equus.feed.infrastructure.kafka.producer.reply

import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.feed.infrastructure.kafka.dto.DeleteReplyEventRequest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ReplyDeletedEventProducerImpl(
    private val kafkaTemplate: KafkaTemplate<String, DeleteReplyEventRequest>
) : ReplyDeletedEventProducer {
    override fun send(deleteReplyEventRequest: DeleteReplyEventRequest) {
        kafkaTemplate.send(KafkaTopics.DELETE_REPLY, deleteReplyEventRequest)
    }
}
