package pi.growmate.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

@EnableRabbit
@Configuration
public class RabbitMQConfig {
    @Value("${rabbitmq.air.humidity.queue.name}")
    private String airHumQueueName;
    @Value("${rabbitmq.soil.humidity.queue.name}")
    private String soilHumQueueName;
    @Value("${rabbitmq.air.temperature.queue.name}")
    private String airTempQueueName;
    @Value("${rabbitmq.exchange.name}")
    private String exchange;

    @Bean
    public Queue airHumQueue() {
        return new Queue(airHumQueueName);
    }

    @Bean
    public Queue airTempQueue() {
        return new Queue(airTempQueueName);
    }

    @Bean
    public Queue soilHumQueue() {
        return new Queue(soilHumQueueName);
    }

    @Bean
    public TopicExchange exchange(){
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding airHumBinding(){
        return BindingBuilder.bind(airHumQueue()).to(exchange()).with(airHumQueueName);
    }

    @Bean
    public Binding airTempBinding(){
        return BindingBuilder.bind(airTempQueue()).to(exchange()).with(airTempQueueName);
    }

    @Bean
    public Binding soilHumBinding(){
        return BindingBuilder.bind(soilHumQueue()).to(exchange()).with(soilHumQueueName);
    }

    @Bean
    public MessageConverter converter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory){
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(converter());
        return rabbitTemplate;
    }
}
