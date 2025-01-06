package ru.yandex.practicum.processor;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import ru.yandex.practicum.kafka.Config;
import ru.yandex.practicum.kafka.telemetry.event.*;
import ru.yandex.practicum.model.Scenario;
import ru.yandex.practicum.model.Sensor;
import org.springframework.beans.factory.annotation.Value;
import ru.yandex.practicum.repository.ScenarioRepository;
import ru.yandex.practicum.repository.SensorRepository;

import java.time.Duration;
import java.util.Collections;


@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class HubEventProcessor implements Runnable {
    final SensorRepository sensorRepository;
    final ScenarioRepository scenarioRepository;
    final Config config;

    @Value("${topics.hubs}")
    String hubTopics;

    @Override
    public void run() {
        Consumer<String, HubEventAvro> consumer =
                new KafkaConsumer<>(config.producerByHubEvent());

        consumer.subscribe(Collections.singleton(hubTopics));

        while (true) {
            try {
                ConsumerRecords<String, HubEventAvro> records = consumer.poll(Duration.ofMillis(1000));

                for(ConsumerRecord<String,HubEventAvro> record : records) {
                    processHubEvent(record.value());
                }
            } catch (WakeupException e) {

            } catch (Exception e ) {
                log.error("ошибка обработки события с хаба");
            } finally {
                consumer.close();
            }

        }
    }

    private void processHubEvent(HubEventAvro hubEvent) {
        String hubId = hubEvent.getHubId();
        switch (hubEvent.getPayload()) {
            case DeviceAddedEventAvro dae -> processEvent(hubId, dae);
            case DeviceRemovedEventAvro dre -> processEvent(hubId, dre);
            case ScenarioAddedEventAvro sae -> processEvent(hubId, sae);
            case ScenarioRemovedEventAvro sre -> processEvent(hubId, sre);
            default -> log.warn("Получено событие неизвестного типа {}", hubEvent);
        }
    }

    //deviceAdd
    private void processEvent(String hubId, DeviceAddedEventAvro dae) {
        log.info("добавление девайса в хаб под id = {}", hubId);
        if (!sensorRepository.existsByIdInAndHubId(Collections.singleton(dae.getId()), hubId)) {
            sensorRepository.save(new Sensor(dae.getId(), hubId, dae.getDeviceType()));
        } else {
            log.info("такой сенсор уже добавлен");
        }
    }

    //remove
    private void processEvent(String hubId, DeviceRemovedEventAvro dre) {
        if(sensorRepository.findByIdAndHubId(hubId, dre.getId()).isPresent()) {
            log.info("Удаление сенсора из хаба с id = {}",hubId);
            sensorRepository.deleteById((long)Integer.parseInt(dre.getId()));
        } else {
            log.info("Сенсор с id = {} не найден",dre.getId());
        }
    }

    //ScenarioAdd
    private void processEvent(String hubId, ScenarioAddedEventAvro sae) {
        log.info("Добавления нового сценария в хаб под id = {}",hubId);
        scenarioRepository.save(new Scenario(hubId, sae.getName()));
    }

    //ScenarioDel
    private void processEvent(String hubId, ScenarioRemovedEventAvro sre) {
        log.info("Удаление из хаба с id = {}",hubId);

        if(scenarioRepository.findByHubIdAndName(hubId, sre.getName()).isPresent()) {
            scenarioRepository.deleteById((long)Integer.parseInt(hubId));
        }
    }
}

