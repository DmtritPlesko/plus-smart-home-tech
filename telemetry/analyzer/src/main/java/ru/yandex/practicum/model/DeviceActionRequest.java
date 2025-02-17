package ru.yandex.practicum.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceActionRequest {
     String hubId;
     String actionType;
     Instant data;
     String action;

    public String toJson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(this);
    }

    public DeviceActionRequest(String hubId,String actionType,Instant data,String action) {
        this.hubId = hubId;
        this.actionType = actionType;
        this.data = data;
        this.action = action;
    }
}
