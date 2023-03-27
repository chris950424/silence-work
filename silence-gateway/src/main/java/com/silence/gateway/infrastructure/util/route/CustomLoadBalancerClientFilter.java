package com.silence.gateway.infrastructure.util.route;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerProperties;
import org.springframework.cloud.client.loadbalancer.LoadBalancerUriTools;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.client.loadbalancer.reactive.ReactiveLoadBalancer;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter;
import org.springframework.cloud.gateway.support.DelegatingServiceInstance;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.net.URI;
import java.util.Objects;

//@Component
public class CustomLoadBalancerClientFilter implements GlobalFilter, Ordered {
    private static final Log log = LogFactory.getLog(org.springframework.cloud.gateway.filter.ReactiveLoadBalancerClientFilter.class);

    @Resource
    private final LoadBalancerClientFactory clientFactory;

    @Resource
    private LoadBalancerProperties properties;

    public CustomLoadBalancerClientFilter(LoadBalancerClientFactory clientFactory, LoadBalancerProperties properties) {
        this.clientFactory = clientFactory;
        this.properties = properties;
    }

    @Override
    public int getOrder() {
        return 10149;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        URI url = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        System.out.println("鉴权url：" + url);
        String schemePrefix = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_SCHEME_PREFIX_ATTR);
        if (url != null && ("lb".equals(url.getScheme()) || "lb".equals(schemePrefix))) {
            ServerWebExchangeUtils.addOriginalRequestUrl(exchange, url);
            if (log.isTraceEnabled()) {
                log.trace(ReactiveLoadBalancerClientFilter.class.getSimpleName() + " url before: " + url);
            }

            return this.choose(exchange).doOnNext((response) -> {
                if (!response.hasServer()) {
                    throw NotFoundException.create(true, "Unable to find instance for " + url.getHost());
                } else {
                    URI uri = exchange.getRequest().getURI();
                    String overrideScheme = null;
                    if (schemePrefix != null) {
                        overrideScheme = url.getScheme();
                    }

                    int id = Integer.parseInt(Objects.requireNonNull(exchange.getRequest().getQueryParams().getFirst("id")));
                    if (id % 2 == 0) {
                        while (!"new".equals(response.getServer().getMetadata().get("version"))) {
                            try {
                                response = this.choose(exchange).toFuture().get();
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }

                    DelegatingServiceInstance serviceInstance = new DelegatingServiceInstance(response.getServer(), overrideScheme);

                    System.out.println(exchange.getRequest().getQueryParams().getFirst("id") + "对应server的version为：" + serviceInstance.getMetadata().get("version"));
                    URI requestUrl = LoadBalancerUriTools.reconstructURI(serviceInstance, uri);
                    if (log.isTraceEnabled()) {
                        log.trace("LoadBalancerClientFilter url chosen: " + requestUrl);
                    }

                    exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, requestUrl);
                }
            }).then(chain.filter(exchange));
        } else {
            return chain.filter(exchange);
        }
    }

    private Mono<Response<ServiceInstance>> choose(ServerWebExchange exchange) {
        URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
        assert uri != null;
        ReactorLoadBalancer<ServiceInstance> loadBalancer = this.clientFactory.getInstance(uri.getHost(), ReactorLoadBalancer.class, new Class[]{ServiceInstance.class});
        if (loadBalancer == null) {
            throw new NotFoundException("No loadbalancer available for " + uri.getHost());
        } else {
            return loadBalancer.choose(this.createRequest());
        }
    }

    private Request createRequest() {
        return ReactiveLoadBalancer.REQUEST;
    }
}