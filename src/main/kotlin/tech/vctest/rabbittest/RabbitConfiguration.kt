package tech.vctest.rabbittest

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.ExchangeBuilder
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile


@Configuration
@EnableRabbit
class RabbitConfiguration {

    @Bean
    fun consumerBatchContainerFactory(connectionFactory: ConnectionFactory): SimpleRabbitListenerContainerFactory? {
        val factory = SimpleRabbitListenerContainerFactory()
        factory.setConnectionFactory(connectionFactory)
        factory.setBatchListener(true) // configures a BatchMessageListenerAdapter
        factory.setBatchSize(5)
        factory.setConsumerBatchEnabled(true)
        return factory
    }

//    @Bean
//    fun channel() = AMQP.Channel()

//    @Profile("!test")
    @Bean
    fun rabbitClient() = RabbitClient()

}