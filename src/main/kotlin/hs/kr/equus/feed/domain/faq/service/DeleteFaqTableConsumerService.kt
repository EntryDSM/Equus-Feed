package hs.kr.equus.feed.domain.faq.service

import hs.kr.equus.feed.domain.faq.domain.repository.FaqRepository
import hs.kr.equus.feed.infrastructure.kafka.configuration.KafkaTopics
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class DeleteFaqTableConsumerService(
    private val faqRepository: FaqRepository
) {
    @Transactional
    @KafkaListener(topics = [KafkaTopics.DELETE_ALL_TABLE], groupId = "\${kafka.consumer.groupId}")
    fun execute() = faqRepository.deleteAll()
}