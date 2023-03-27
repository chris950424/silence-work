package com.silence.order.infrastructure.ws.impl;

import com.silence.order.infrastructure.ws.WsService;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Service;

import static com.silence.order.infrastructure.ws.WebSocketServerHandler.ST_USER;

/**
 * WsServiceImpl
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/24
 */
@Service
public class WsServiceImpl implements WsService {
    @Override
    public void send(Long userId, String status) {
        Channel channel = ST_USER.get(userId);
        channel.writeAndFlush(new TextWebSocketFrame(status));
    }
}
