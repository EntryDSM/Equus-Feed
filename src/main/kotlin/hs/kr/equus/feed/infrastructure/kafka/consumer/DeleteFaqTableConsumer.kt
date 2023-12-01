package hs.kr.equus.feed.infrastructure.kafka.consumer

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteFaqTableConsumer(
    private val faqRepository: FaqRepository
) {
    @KafkaListener(topics = [KafkaTopics.DELETE_ALL_TABLE], groupId = "delete-all-table-faq")
    @Transactional
    fun execute() = faqRepository.deleteAll()
}
