package com.silence.order.infrastructure.job.Executors;

import com.alibaba.fastjson.JSON;
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import com.silence.api.PayDto;
import com.silence.order.infrastructure.dao.OrderMapper;
import com.silence.order.infrastructure.facade.PayFacade;
import com.silence.order.infrastructure.job.annotation.JobScheduled;
import com.silence.order.infrastructure.repository.DO.OrderDO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * TextExecutor
 *
 * @author leo
 * @version 1.1.0
 * @date 2022/1/24
 */
//@Component
//@JobScheduled(jobName = "test", cron = "1 * * * * ?")
public class TextExecutor implements SimpleJob {

    Logger logger = LoggerFactory.getLogger(TextExecutor.class);

    private static final Executor EXECUTOR = new ThreadPoolExecutor(
            2,
            5,
            3,
            TimeUnit.SECONDS,
            new LinkedBlockingDeque<>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    @Resource
    PayFacade payFacade;

    @Resource
    private OrderMapper mapper;


    @Override
    public void execute(ShardingContext shardingContext) {
        int status = 1;

        List<OrderDO> orderDos = mapper.selectAllOrder(status);
        logger.info("创建的订单：{}", JSON.toJSONString(orderDos));
        List<OrderDO> invalidOrders = orderDos.stream().filter(a -> !(justiceTime(a.getCreateTime())))
                .collect(Collectors.toList());
        invalidOrders.forEach(a -> {
            a.setStatus(3);
        });
        logger.info("无效的订单：{}", JSON.toJSONString(invalidOrders));
        if (!CollectionUtils.isEmpty(invalidOrders)) {
            int i = mapper.updateBatch(invalidOrders);
        }
        List<OrderDO> queryOrders = new ArrayList<>();
        orderDos.stream()
                .filter(a -> justiceTime(a.getCreateTime()))
                .forEach(value -> {
                    PayDto payDto = new PayDto();
                    payDto.setOrderNo(String.valueOf(value.getOrderNo()));
                    payDto.setPayType(value.getPayType());
                    PayDto query = payFacade.query(payDto);
                    if (query != null) {
                        OrderDO orderDO = new OrderDO();
                        orderDO.setOrderNo(Long.parseLong(query.getOrderNo()));
                        orderDO.setTransactionId(query.getTransactionId());
                        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-hh HH:mm:ss");
                        try {
                            orderDO.setEndTime(f.parse(query.getEndTime()));
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        orderDO.setStatus(2);
                        queryOrders.add(orderDO);
                    }
                });
        logger.info("查询的订单：{}", JSON.toJSONString(queryOrders));
        if (!CollectionUtils.isEmpty(queryOrders)) {
            int j = mapper.updateBatch(queryOrders);
        }
    }

    private boolean justiceTime(Date createTime) {
        return LocalDateTime
                .now()
                .minus(10, ChronoUnit.MINUTES)
                .isBefore(LocalDateTime.ofInstant(createTime.toInstant(), ZoneId.systemDefault()));
    }
}
