package com.silence.order.infrastructure.ws;

public interface WsService {
    void send(Long userId, String status);
}
