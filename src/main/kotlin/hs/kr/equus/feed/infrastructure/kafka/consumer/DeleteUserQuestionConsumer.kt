package hs.kr.equus.feed.infrastructure.kafka.consumer

import com.fasterxml.jackson.databind.ObjectMapper
import hs.kr.equus.feed.domain.question.domain.repository.QuestionRepository
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import hs.kr.equus.feed.infrastructure.kafka.dto.DeletedUserInfo
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class DeleteUserQuestionConsumer(
    private val questionRepository: QuestionRepository,
    private val objectMapper: ObjectMapper
) {
    @KafkaListener(
        topics = [KafkaTopics.DELETE_USER],
        groupId = "delete-user-question",
        containerFactory = "kafkaListenerContainerFactory"
    )
    @Transactional
    fun execute(deletedUserInfo: String) {
        println(deletedUserInfo)
        val deleteUserInfo = objectMapper.readValue(deletedUserInfo, DeletedUserInfo::class.java)
        questionRepository.deleteAllByUserId(deleteUserInfo.userId)
    }
}
