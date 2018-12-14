package cn.ldm.thread.utills;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: thread
 * @description
 * @author: luoyongxiang
 * @create: 2018-12-13 20:15
 **/
public class RabbitmqUtills {
    @Autowired
    private AmqpAdmin amqpAdmin;


    public void getDirectExchange(String exchange){
        DirectExchange directExchange = new DirectExchange (exchange,true,true);
        amqpAdmin.declareExchange (directExchange);
    }

    public void getTopicExchange(String exchange){
            TopicExchange topicExchange = new TopicExchange (exchange,true,true);
            amqpAdmin.declareExchange (topicExchange);
    }


    public void getQueue(String string){
        Queue queue = new Queue (string,true);
        amqpAdmin.declareQueue (queue);
    }

    public static void getBinding(AmqpAdmin amqpAdmin,String que, String exchange, String routingkey, Map map){
        Queue queue = new Queue (que,true);
        amqpAdmin.declareQueue (queue);
        DirectExchange directExchange = new DirectExchange (exchange,true,true);
        amqpAdmin.declareExchange (directExchange);
        BindingBuilder.bind (queue).to (directExchange).with (routingkey);
    }

}
