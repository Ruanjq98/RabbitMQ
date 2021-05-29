package com.qlu.rjq.workQuery;

import com.qlu.rjq.ConnectUntils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 17:27$
 */
public class consumer1 {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectUntils.getConnection();
        final Channel channel = connection.createChannel();
        channel.queueDeclare("work",true,false,false,null );
        channel.basicQos( 1 );
        // 第二个参数 自动确认，收到就确认然后删除掉消息
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者1" +new String(body));
                // 手动确认
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });


    }
}
