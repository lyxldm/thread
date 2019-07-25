package cn.ldm.thread.controller;

import cn.ldm.thread.utills.DateUtills;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: thread
 * @description 测试Rabbitmq
 * @author: luoyongxiang
 * @create: 2018-12-13 16:24
 **/
@RestController
public class RabbitmqController
{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private MessagePostProcessor messagePostProcessor;
    /**
    * @Description  测试延迟队列，优先级
    * @Param:  * @param null
    * @return:
    * @Author: luoyongxiang
    * @Date: 2018/12/14
    */

    @RequestMapping("delayQueue")
    public void rabbitmq(){
        System.out.println (5555 );
        rabbitTemplate.convertAndSend ("delaychange", "delaykey","你好", messagePostProcessor);
        /*测试优先级*/
        /*rabbitTemplate.convertAndSend ("delaychange", "delaykey", "你好aa", new MessagePostProcessor ( ) {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                System.out.println ("发送消息时间"+DateUtills.getNowDate ());
                message.getMessageProperties ().setPriority (10);
                return message;
            }
        });*/

    }

    @RabbitListener(queues = "deadqueue")
    public void get(String booking) {
        System.out.println (DateUtills.getNowDate ());
        System.out.println("收到延时消息了" + booking);
    }





}
