package ru.yandex.practicum.processor;

import com.google.protobuf.Timestamp;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.WakeupException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.practicum.event.sensor.*;
import ru.yandex.practicum.enums.ActionType;
import ru.yandex.practicum.grpc.telemetry.event.ActionTypeProto;
import ru.yandex.practicum.grpc.telemetry.event.DeviceActionProto;
import ru.yandex.practicum.kafka.Config;
import ru.yandex.practicum.kafka.telemetry.event.SensorsSnapshotAvro;
import ru.yandex.practicum.model.DeviceActionRequest;
import ru.yandex.practicum.model.Scenario;
import ru.yandex.practicum.repository.ScenarioRepository;
import ru.yandex.practicum.service.GrpcClientService;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SnapshotProcessor implements Runnable {
    final ScenarioRepository scenarioRepository;
    Consumer<String, SensorsSnapshotAvro> consumer;
    @Value("${topics.snapshots}")
    String snapshotTopics;
    final Config config = new Config();
    final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(config.producerBySnapshot());
    final GrpcClientService grpcClientService;

    @Override
    public void run() {
        consumer = new KafkaConsumer<>(config.producerBySnapshot());

        consumer.subscribe(Arrays.asList(snapshotTopics));

        while(true) {
            try {

            } catch (WakeupException e) {

            } catch (Exception e) {
                log.error("Ошибка при обработке снепшота");
            } finally {
                consumer.close();
            }
            ConsumerRecords<String,SensorsSnapshotAvro> records = consumer.poll(Duration.ofMillis(1000));

            for(ConsumerRecord<String,SensorsSnapshotAvro> record : records) {
                processSnapshot(record.value());
            }
        }
    }

    private void processSnapshot(SensorsSnapshotAvro snapshot) {
        log.info("Обработка снепшота из хаба с id = {}", snapshot.getHubId());

        List<Scenario> conditionsList = scenarioRepository.findByHubId(snapshot.getHubId());

        for (Scenario temp : conditionsList) {
            switch (snapshot.get(String.valueOf(temp.getHudId()))) {
                case MotionSensorEvent mse -> processSensor(snapshot.getHubId(), mse);
                case TemperatureSensorEvent tse -> processSensor(snapshot.getHubId(), tse);
                case LightSensorEvent lse -> processSensor(snapshot.getHubId(), lse);
                case ClimateSensorEvent cse -> processSensor(snapshot.getHubId(), cse);
                case SwitchSensorEvent sse -> processSensor(snapshot.getHubId(), sse);
                default -> log.warn("Получено событие неизвестного типа: {}", snapshot);
            }
        }

    }

    private void processSensor(String hubId, MotionSensorEvent mse) {
        if (mse.getMotion()) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                                    .setHubId(hubId)
                                    .setScenarioName(scenario.getName())
                                    .setTimestamp(Timestamp.newBuilder()
                                            .setSeconds(Instant.now().toEpochMilli())
                                            .setNanos(Instant.now().getNano())
                                            .build())
                                    .setAction(DeviceActionProto.newBuilder()
                                            .setSensorId(mse.getId())
                                            .setType(ActionTypeProto.TURN_ON_THE_LIGHT_BULB)
                                            .build())
                                    .build());
                }
            } catch (Exception e) {
                log.error("Ошибка при отправке данных");
            }

        }
    }

    private void processSensor(String hubId, TemperatureSensorEvent tse) {
        if (tse.getTemperatureC() < 15) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(tse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_AIR_CONDITIONER)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Ошибка при отправке данных на включение теплого пола");
            }

        } else if (tse.getTemperatureC() > 25) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(tse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_HEATING)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Ошибка при отправке данных на включение кондиционера");
            }
        }
    }

    private void processSensor(String hubId, LightSensorEvent lse) {
        log.info("Включаю доп освещение");
        if (lse.getLuminosity() < 40) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(lse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_LIGHT_BULB)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Ошибка при отправке данных на включение доп освещения");
            }

        }
    }

    private void processSensor(String hubId, ClimateSensorEvent cse) {

        if (cse.getTemperatureC() < 15) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(cse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_HEATING)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Ошибка при отправке данных на включение теплого пола от сенсора климата");
            }
        } else if (cse.getTemperatureC() > 25) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(cse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_AIR_CONDITIONER)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Ошибка при отправке данных на включение кондиционера от сенсора климата");
            }
        }

        if (cse.getCo2Level() > 50) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(cse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_AIR_FRESHENER)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Ошибка отправки данных на определение уровня чистоты воздуха");
            }

        }

        if (cse.getHumidity() > 80) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(cse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_DEHUMIDIFIER)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Невозможно включить осушитель воздуха");
            }

        } else if (cse.getHumidity() < 40) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(cse.getId())
                                    .setType(ActionTypeProto.TURN_ON_THE_HUMIDIFIER)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Невозможно включить увлажнитель воздуха");
            }
        }
    }

    private void processSensor(String hubId, SwitchSensorEvent sse) {
        if (!sse.getState()) {
            try {
                for(Scenario scenario : scenarioRepository.findByHubId(hubId)) {
                    grpcClientService.send(ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest.newBuilder()
                            .setHubId(hubId)
                            .setScenarioName(scenario.getName())
                            .setTimestamp(Timestamp.newBuilder()
                                    .setSeconds(Instant.now().toEpochMilli())
                                    .setNanos(Instant.now().getNano())
                                    .build())
                            .setAction(DeviceActionProto.newBuilder()
                                    .setSensorId(sse.getId())
                                    .setType(ActionTypeProto.ACTIVATION)
                                    .build())
                            .build());
                }
            } catch (Exception e) {
                log.error("Невозможно включить датчик");
            }
        }
    }
}
