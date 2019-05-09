package com.company.project.linstener;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfiguration {

    //简单发送队列
    public static final String SIMPLE_QUEUE            = "tea";
    // 发布/订阅模式   
    public static final String FANOUT_EXCHANGE         = "fanout_exchange";

    //路由模式
    public static final String DIRECT_EXCHANGE         = "direct_exchange";

    //交换机——TopicExchange  规则
    public static final String TOPIC_EXCHANGE          = "topic_exchange";

    //保存用户-交换机名称
    public static final String SAVE_USER_EXCHANGE_NAME = "user.save.direct.exchange.name";
    //保存用户-队列名称
    public static final String SAVE_USER_QUEUE_NAME    = "user.save.queue.name";

    public static final String SAVE_USER_QUEUE_NAME2   = "user.save.queue.name2";

    //保存用户-队列路由键
    // public static final String SAVE_USER_QUEUE_ROUTE_KEY = "user.save.queue.route.key";

    /**
     *
     *配置交换机实例   创建广播形式的交换机  发布/订阅模式
     * @return
     */

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(SAVE_USER_EXCHANGE_NAME);

    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("jake.direct.exchange");
    }

    /**
    * 配置队列实例，true并且设置持久化队列
    *
    * @return
    */
    @Bean
    public Queue queue() {
        return new Queue(SAVE_USER_QUEUE_NAME, true);

    }

    @Bean
    public Queue queue2() {
        return new Queue(SAVE_USER_QUEUE_NAME2, true);

    }

    /**
    * 将队列绑定到交换机上，并设置消息分发的路由键
    *
    * @return
    */
    @Bean
    public Binding bingQueue1ToExchange() {

        return BindingBuilder.bind(queue()).to(fanoutExchange());

    }

    @Bean
    public Binding bingQueue2ToExchange(@Qualifier String queue) {

        //   return BindingBuilder.bind(queue2()).to(fanoutExchange());
        return BindingBuilder.bind(queue2()).to(directExchange()).with("order");

    }

}
