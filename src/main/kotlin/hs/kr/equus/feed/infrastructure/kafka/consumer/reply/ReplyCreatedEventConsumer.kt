package hs.kr.equus.feed.infrastructure.kafka.consumer.reply

import hs.kr.equus.feed.domain.question.service.MarkAsRepliedService
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.feed.infrastructure.kafka.dto.CreateReplyEventRequest
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ReplyCreatedEventConsumer(
    private val markAsRepliedService: MarkAsRepliedService
) {
    @KafkaListener(
        topics = [KafkaTopics.CREATE_REPLY],
        groupId = "entry",
        containerFactory = "createReplyDtoChangeListener"
    )
    fun createReply(createReplyEventRequest: CreateReplyEventRequest) {
        markAsRepliedService.execute(createReplyEventRequest.questionId)
    }
}
