package com.bank.gateway.routes;

import com.bank.gateway.model.Payment;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class PaymentRoute extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("direct:processPayment")
                .log("Получен платёж: ${body}")
                .to("jpa://com.bank.gateway.model.Payment") // Сохраняем в БД
                .setHeader(KafkaConstants.KEY, simple("${body.id}"))
                .to("kafka:payments?brokers=localhost:9092") // Отправляем в Kafka
                .log("Платеж обработан и отправлен в Kafka: ${body.id}");

        from("kafka:payments?brokers=localhost:9092&groupId=bank-gateway")
                .log("Получен платеж из Kafka: ${body}")
                .process(exchange -> {
                    Payment payment = exchange.getIn().getBody(Payment.class);
                    // Дополнительная обработка платежа
                });
    }
}
