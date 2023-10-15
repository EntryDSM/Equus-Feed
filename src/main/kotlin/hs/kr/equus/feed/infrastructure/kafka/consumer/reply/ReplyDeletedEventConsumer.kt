package hs.kr.equus.feed.infrastructure.kafka.consumer.reply

import hs.kr.equus.feed.domain.question.service.MarkAsNotRepliedService
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.feed.infrastructure.kafka.dto.DeleteReplyEventRequest
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class ReplyDeletedEventConsumer(
    private val markAsNotRepliedService: MarkAsNotRepliedService
) {
    @KafkaListener(
        topics = [KafkaTopics.DELETE_REPLY],
        groupId = "entry",
        containerFactory = "deleteReplyDtoChangeListener"
    )
    fun deleteReply(deleteReplyEventRequest: DeleteReplyEventRequest) {
        markAsNotRepliedService.execute(deleteReplyEventRequest.questionId)
    }
}
