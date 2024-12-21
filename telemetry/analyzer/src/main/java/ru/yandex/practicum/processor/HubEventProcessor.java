package ru.yandex.practicum.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import ru.yandex.practicum.kafka.telemetry.event.HubEventAvro;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import static ru.yandex.practicum.kafka.Config.producerByHubEvent;


@Slf4j
public class HubEventProcessor implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Consumer<String, HubEventAvro> consumer =
                        new KafkaConsumer<>(producerByHubEvent());

                consumer.subscribe(Collections.singleton("telemetry.hubs.v1"));

                while (!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, HubEventAvro> records =
                            consumer.poll(Duration.ofMillis(100));

                    for (ConsumerRecord<String, HubEventAvro> record : records) {
                        log.info("Received event: {}", record.value());
                        processHubEvent(record.value());
                    }
                }
            } catch (Exception e) {
                log.error("Error processing hub events", e);
            }
        }
    }

    private void processHubEvent(HubEventAvro event) {
        // Логика обработки события
    }
}

