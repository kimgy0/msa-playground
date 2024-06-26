package com.example.orderservice.messagequeue;

import com.example.orderservice.dto.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    List<Field> fields = Arrays.asList(
            new Field("string", true, "order_id"),
            new Field("string", true, "user_id"),
            new Field("string", true, "product_id"),
            new Field("string", true, "qty_id"),
            new Field("string", true, "unit_price"),
            new Field("string", true, "total_price")
    );

    Schema schema = Schema.builder().fields(fields).optional(false).name("orders").type("struct").build();


    public OrderDto send(String topic, OrderDto orderDto) throws JsonProcessingException {

        Payload payload = Payload.builder()
                                            .order_id(orderDto.getOrderId())
                                            .user_id(orderDto.getUserId())
                                            .product_id(orderDto.getProductId())
                                            .qty(orderDto.getQty())
                                            .unit_price(orderDto.getUnitPrice())
                                            .total_price(orderDto.getTotalPrice())
                                            .build();

        KafkaOrderDto kafkaOrderDto = new KafkaOrderDto(schema, payload);

        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = "";

        jsonInString = mapper.writeValueAsString(kafkaOrderDto);

        kafkaTemplate.send(topic, jsonInString);

        log.info("Order Producer sent data from the Order microservice: " + kafkaOrderDto);

        return orderDto;

    }
}
