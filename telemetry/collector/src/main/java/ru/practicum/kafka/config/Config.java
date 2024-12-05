package ru.practicum.kafka.config;

import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
@ConfigurationProperties
public class Config {

    @Value("${server}")
    String server;

    @Value("${key_serialize_class}")
    String keySerializeClass;

    @Value("${value_serialize_class}")
    String valueSerializeClass;

    @Bean
    KafkaProducer<String, SpecificRecordBase> producer() {

        Properties property = new Properties();

        property.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        property.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySerializeClass);
        property.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializeClass);


        return new KafkaProducer<>(property);
    }
}
