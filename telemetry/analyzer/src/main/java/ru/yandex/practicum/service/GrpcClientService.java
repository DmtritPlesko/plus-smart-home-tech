package ru.yandex.practicum.service;

import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.grpc.telemetry.hubrouter.HubRouterControllerGrpc;
import ru.yandex.practicum.grpc.telemetry.event.DeviceActionRequest;

@Slf4j
@Service
public class GrpcClientService {
    private final HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient;

    public GrpcClientService(@GrpcClient("hub-router")
                             HubRouterControllerGrpc.HubRouterControllerBlockingStub hubRouterClient) {
        this.hubRouterClient = hubRouterClient;
    }

    public void send(DeviceActionRequest dar) {
        if(hubRouterClient.handleDeviceAction(dar).isInitialized()) {
            log.info("Есть ответ");
        } else {
            log.error("Нет ответа от hub-router");
        }
    }
}
