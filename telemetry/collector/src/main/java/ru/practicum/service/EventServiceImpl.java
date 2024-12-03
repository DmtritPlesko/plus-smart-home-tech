package ru.practicum.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.practicum.event.hub.DeviceAddedEvent;
import ru.practicum.event.hub.DeviceRemovedEvent;
import ru.practicum.event.sensor.*;
import ru.practicum.mapper.hub.HubMapper;
import ru.practicum.mapper.sensor.SensorMapper;
import ru.practicum.model.hub.HubEvent;
import ru.practicum.model.sensor.SensorEvent;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class EventServiceImpl implements EventService {

    @Value("${topics.sensor}")
    final String sensorTopic;

    @Value("${topics.hubs}")
    final String hubTopic;

    final KafkaProducer<String, SpecificRecordBase> kafkaProducer;
    final SensorMapper sensorMapper;
    final HubMapper hubMapper;

    @Override
    public <T extends SensorEvent> void collectSensorEvent(T event) {
        if (event instanceof ClimateSensorEvent) {
            kafkaProducer.
                    send(new ProducerRecord<>(sensorTopic,
                            sensorMapper.toClimateEventAvro((ClimateSensorEvent) event)));
        } else if (event instanceof LightSensorEvent) {
            kafkaProducer.
                    send(new ProducerRecord<>(sensorTopic,
                            sensorMapper.toLightSensorEventAvro((LightSensorEvent) event)));
        } else if (event instanceof MotionSensorEvent) {
            kafkaProducer.
                    send(new ProducerRecord<>(sensorTopic,
                            sensorMapper.toMotionSensorEventAvro((MotionSensorEvent) event)));
        } else if (event instanceof SwitchSensorEvent) {
            kafkaProducer.
                    send(new ProducerRecord<>(sensorTopic,
                            sensorMapper.toSwitchSensorEventAvro((SwitchSensorEvent) event)));
        } else if (event instanceof TemperatureSensorEvent) {
            kafkaProducer.
                    send(new ProducerRecord<>(sensorTopic,
                            sensorMapper.toTemperatureSensorEventAvro((TemperatureSensorEvent) event)));
        }
    }

    @Override
    public <T extends HubEvent> void collectHubEvent(T event) {
        if (event instanceof DeviceAddedEvent) {
            kafkaProducer.
                    send(new ProducerRecord<>(hubTopic, hubMapper.toDeviceAddedEventAvro((DeviceAddedEvent) event)));
        } else if (event instanceof DeviceRemovedEvent) {
            kafkaProducer.
                    send(new ProducerRecord<>(hubTopic, hubMapper.toDeviceRemovedEventAvro((DeviceRemovedEvent) event)));

        }
    }

}
