package cst438.fr.fe.domain;

import org.springframework.amqp.core.FanoutExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigPublisher {
    @Bean
    public FanoutExchange fanout() {
        return new FanoutExchange("flight-reservation");
    }
}