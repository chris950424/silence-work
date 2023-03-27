package com.silence.gateway.infrastructure.util.ws;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Objects;

import static com.silence.gateway.infrastructure.util.ws.Constant.*;
import static org.springframework.cloud.gateway.support.ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR;

/**
 * @author 谢飞机
 * @date 2021-08-25
 */
@Component
public class WebsocketFilter implements GlobalFilter, Ordered {


    /**
     * @param exchange ServerWebExchange是一个HTTP请求-响应交互的契约。
     *                 提供对HTTP请求和响应的访问，
     *                 并公开额外的 服务器 端处理相关属性和特性，如请求属性
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        this.changeSchemeIfIsWebSocketUpgrade(exchange);
        URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);

        String scheme = requestUrl.getScheme();
        if (!Objects.equals(WS_PROTOCOL, scheme) && !Objects.equals(WSS_PROTOCOL, scheme)) {

            return chain.filter(exchange);

        } else if (Objects.equals(DEFAULT_FILTER_PATH, requestUrl.getPath())) {
            scheme = convertWsToHttp(scheme);
            //TODO  uri: lb:ws://ws-server:12000/  方式的端口没有生效 手动赋值port  后续再研究
            URI wsRequestUrl = UriComponentsBuilder.fromUri(requestUrl).scheme(scheme).port(WS_SERVER_PORT).build().toUri();

            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, wsRequestUrl);
        }
        return chain.filter(exchange);
    }

    private void changeSchemeIfIsWebSocketUpgrade(ServerWebExchange exchange) {
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String upgrade = headers.getUpgrade();
        assert upgrade != null;
        if (DEFAULT_FILTER_UPGRADE.equals(upgrade)) {
            URI requestUrl = exchange.getRequiredAttribute(GATEWAY_REQUEST_URL_ATTR);
            URI wsRequestUrl = UriComponentsBuilder.fromUri(requestUrl).scheme(WS_PROTOCOL).build().toUri();
            exchange.getAttributes().put(GATEWAY_REQUEST_URL_ATTR, wsRequestUrl);        }
    }

    /**
     * org.springframework.cloud.gateway.filter.WebsocketRoutingFilter
     * 这个类的order  Ordered.LOWEST_PRECEDENCE - 1
     * 此类需要在它之前就执行所以
     * Ordered.LOWEST_PRECEDENCE - 2;
     *
     * @return
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 2;
    }
    /**
     *  将 WS 转换成 HTTP
     * @param scheme
     * @return
     */
    private String convertWsToHttp(String scheme) {

        scheme = scheme.toLowerCase();

        if(Objects.equals(WS_PROTOCOL,scheme)){

            return HTTP_PROTOCOL;

        }else if(Objects.equals(WSS_PROTOCOL ,scheme)){

            return HTTPS_PROTOCOL;

        }else {

            return scheme;
        }

    }
}