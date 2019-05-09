package com.company.project.linstener;

import java.util.UUID;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.stereotype.Component;

@Component
public class Sender implements RabbitTemplate.ConfirmCallback {

    private static Logger  logger = (Logger) LoggerFactory.getLogger(Sender.class);

    @Resource
    private RabbitTemplate rabbitTemplate;

    public void send(String msg) {
        //设置回调对象
        rabbitTemplate.setConfirmCallback(this);
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend("SAVE_USER_EXCHANGE_NAME", "", msg, correlationData);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("confirm回调方法>>>>>>>>>>>>>回调消息ID为: " + correlationData.getId());
        if (ack) {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送成功");
        } else {
            logger.info("confirm回调方法>>>>>>>>>>>>>消息发送失败" + cause);
        }

    }
}