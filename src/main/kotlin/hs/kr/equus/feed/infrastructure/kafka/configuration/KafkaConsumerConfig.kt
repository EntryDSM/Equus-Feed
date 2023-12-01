package hs.kr.equus.feed.infrastructure.kafka.configuration

import com.fasterxml.jackson.databind.JsonDeserializer
import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.common.serialization.StringDeserializer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    private val kafkaProperty: KafkaProperty,
) {
    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()

        factory.setConcurrency(2)
        factory.consumerFactory = DefaultKafkaConsumerFactory(consumerFactoryConfig())
        factory.containerProperties.pollTimeout = 500
        return factory
    }

    private fun consumerFactoryConfig(): Map<String, Any> {
        val props: MutableMap<String, Any> = HashMap()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = kafkaProperty.serverAddress
        props[ConsumerConfig.ISOLATION_LEVEL_CONFIG] = "read_committed" // producer가 트랙잭션을 성공한 메세지를 커밋한 메세지만 읽어올 수 있음
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = "false" // true로 한다면 일부 메세지들이 DB에 저장이 안된 상태로 컨슈머 장애가 발생했을때 메세지가 손실될 수 있음
        props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "latest" // 가장 최근에 생성된 메세지를 소비하도록 설정
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer::class.java
        props[ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG] = 5000
        return props
    }
}
