package com.company.project.linstener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.messaging.handler.annotation.Payload;

import com.rabbitmq.client.Channel;

/**
 * mq接收队列
 *
 * @author linxk
 * @version $Id: GatewayCallBackListener.java, v 0.1 2017年12月11日 下午2:41:38 linxk Exp $
 */
@RabbitListener(queues = "threshold")
public class ThresholdListener {

    private static final Logger log = LoggerFactory.getLogger(ThresholdListener.class);

    @Bean
    public Queue fooQueue() {
        return new Queue("threshold");
    }

    /**
     * 接收到消息通知，进行保存处理
     * 1.根据keyCode，截取key,获取阀值配置。
     * 2.根据类型，分别插入对应的数据库表中，查询是否有keyCode记录，有，值进行加和，无，新增。
     *
     * 
     * @param recordNo key
     * @param value 值
     * @throws Exception 
     */
    @RabbitHandler
    public void process(@Payload String parameters, Channel channel,
                        Message message) throws Exception {
        log.error("threshold消息通知参数recordNo:" + parameters);

        // Object o = JSON.parse(parameters);

        System.err.println("消息主动消费输出" + new String(parameters));
        System.err.println("消息主动消费输出" + new String(message.getBody()));
        System.err.println(message.getMessageProperties().getDeliveryTag());

        //        if (message.getMessageProperties().getDeliveryTag() == 1
        //            || message.getMessageProperties().getDeliveryTag() == 2) {
        // throw new Exception();
        //   }

        //            //确认消息发送
        //            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        //            if (message.getMessageProperties().getRedelivered()) {
        //                System.out.println("消息已重复处理失败,拒绝再次接收...");
        //                channel.basicReject(message.getMessageProperties().getDeliveryTag(), true); // 拒绝消息
        //            } else {
        //                System.out.println("消息即将再次返回队列处理...");
        //                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true); // requeue为是否重新回到队列
        //            }

    }
}