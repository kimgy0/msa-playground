package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.OrderDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public OrderDto send(String topic, OrderDto orderDto) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = "";

        jsonInString = mapper.writeValueAsString(orderDto);

        kafkaTemplate.send(topic, jsonInString);

        log.info("Kafka Producer sent data from the Order microservice: " + orderDto);

        return orderDto;

    }
}
