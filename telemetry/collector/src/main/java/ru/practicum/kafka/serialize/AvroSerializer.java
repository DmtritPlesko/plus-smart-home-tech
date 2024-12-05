package ru.practicum.kafka.serialize;

import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificDatumWriter;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;


public class AvroSerializer implements Serializer<SpecificRecordBase> {

    @Override
    public byte[] serialize(String topic, SpecificRecordBase data) {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            BinaryEncoder encoder = null;
            encoder = EncoderFactory.get().binaryEncoder(out, encoder);

            DatumWriter<SpecificRecordBase> writer = new SpecificDatumWriter<>(data.getSchema());

            writer.write(data, encoder);

            encoder.flush();

            return out.toByteArray();
        } catch (Exception e) {
            throw new SerializationException("Ошибка при сериализация данных в топик " + topic, e);
        }
    }

}
