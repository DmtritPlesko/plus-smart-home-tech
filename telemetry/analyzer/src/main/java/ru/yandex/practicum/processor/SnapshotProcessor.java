package ru.yandex.practicum.processor;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;
import static ru.yandex.practicum.kafka.Config.producerBySnapshot;
import java.time.Duration;
import java.util.Arrays;

@Slf4j
@Component
public class SnapshotProcessor implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                // Подпись на топик с снимками
                Consumer<String, SensorsSnapshotAvro> consumer =
                        new KafkaConsumer<>(producerBySnapshot());

                consumer.subscribe(Arrays.asList("sensors_snapshots"));

                while (!Thread.currentThread().isInterrupted()) {
                    ConsumerRecords<String, SensorsSnapshotAvro> records =
                            consumer.poll(Duration.ofMillis(100));

                    for (ConsumerRecord<String, SensorsSnapshotAvro> record : records) {
                        log.info("Received snapshot: {}", record.value());
                        processSnapshot(record.value());
                    }
                }
            } catch (Exception e) {
                log.error("Error processing snapshots", e);
            }
        }
    }

    private void processSnapshot(SensorsSnapshotAvro snapshot) {
        // Логика обработки снимка состояния
    }
}
