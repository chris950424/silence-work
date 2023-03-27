package com.silence.gateway.infrastructure.util.ws;

public interface Constant {

    /**
     * 客户端请求地址  比如:ws://127.0.0.1:8888/websocket
     */
    String DEFAULT_FILTER_PATH = "/websocket";

    /**
     * ws 协议
     */
    String WS_PROTOCOL = "ws";

    /**
     * 带有SSL证书的ws协议
     */
    String WSS_PROTOCOL = "wss";

    /**
     * http协议
     */
    String HTTP_PROTOCOL = "http";

    /**
     * https协议
     */
    String HTTPS_PROTOCOL = "https";

    /**
     * ws 服务的端口
     */
    Integer WS_SERVER_PORT = 12000;

    /**
     * 服务名称
     */
    String  SERVER_NAME = "ws-server";


    /**
     * 服务名称
     */
    String DEFAULT_FILTER_UPGRADE = "websocket";

}
