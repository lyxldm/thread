package cn.ldm.thread.conf;

import cn.ldm.thread.utills.DateUtills;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.HashMap;
import java.util.Map;

/**
 * @program: thread
 * @description
 * @author: luoyongxiang
 * @create: 2018-12-13 20:02
 **/
@Configuration
public class RabbitmqConfig {
    @Bean
    public Queue queue01() {
        Queue queue = new Queue ("queue01", true);
        return queue;
    }

    @Bean
    public DirectExchange DirectExchange(){
        DirectExchange directExchange = new DirectExchange ("exchange", true, false);
        return directExchange;
    }

    @Bean
    public Binding binding001() {
        /*
        * 有发送消息时自动声明queue，exchange，binding等
        * */
        return BindingBuilder.bind(queue01()).to(DirectExchange()).with("spring");
    }

    /*缓存队列*/
    @Bean
    public Queue DelayQueue() {
        Map<String, Object> params = new HashMap<>();
        //设置消息过期时间
        params.put("x-message-ttl", 5 * 1000);
        //设置优先级
        params.put("x-max-priority",10);
        // x-dead-letter-exchange 声明了队列里的死信转发到的DLX名称，
        params.put("x-dead-letter-exchange", "deadchange");
        // x-dead-letter-routing-key 声明了这些死信在转发时携带的 routing-key 名称。
        params.put("x-dead-letter-routing-key", "deadkey");
        return new Queue("delayqueue", true, false, false, params);
    }
    @Bean
    public DirectExchange delayExchange() {
        return new DirectExchange("delaychange");
    }

    @Bean
    public Binding deadBinding(){
        return BindingBuilder.bind (DelayQueue ()).to (delayExchange ()).with ("delaykey");
    }


    /*
    * 死心队列
    * */
    @Bean
    public Queue deadQueue() {
        return new Queue("deadqueue", true);
    }

    @Bean
    public TopicExchange deadTopicExchange() {
        return new TopicExchange("deadchange");
    }
    @Bean
    public Binding orderBinding() {
        //  如果要让延迟队列之间有关联,这里的 routingKey 和 绑定的交换机很关键
        return BindingBuilder.bind(deadQueue()).to(deadTopicExchange()).with("deadkey");
    }





    /*
    * 消息处理类
    * */
    @Bean
    public MessagePostProcessor essagePostProcessor(){
        return new MessagePostProcessor ( ) {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println ("发送消息时间"+DateUtills.getNowDate ());
                //也可以设置消息过期时间
                //message.getMessageProperties().setExpiration (5000+"");
                message.getMessageProperties ().setPriority (1);
                return message;
            }
        };
    }



}
