package tech.vctest.rabbittest

import com.rabbitmq.client.AMQP
import com.rabbitmq.client.Channel
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AcknowledgeMode
import org.springframework.amqp.core.Message
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.amqp.support.AmqpHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.messaging.handler.annotation.Headers
import org.springframework.messaging.handler.annotation.Payload

class RabbitClient(/*private val channel: Channel*/) {

    private val logger = LoggerFactory.getLogger(RabbitClient::class.java)

    @RabbitListener(queues = ["test.batch"], containerFactory = "consumerBatchContainerFactory"/*, ackMode = "MANUAL"*/)
    fun consume(messages: Message, channel: Channel) {
        logger.info("consuming messages: $messages")
//        messages..forEach { message ->
//            logger.info("msg: $message")
//            logger.info("received message: ${message.body}")
//            message.messageProperties.deliveryTag
//        }
        logger.info("finished")
//        Message("".toByteArray()).messageProperties.deliveryTag
//        headers["id"].
//        channel.basicAck(tag, true)
    }

}