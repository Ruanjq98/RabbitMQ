package com.qlu.rjq.workQuery;

import com.qlu.rjq.ConnectUntils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 17:27$
 */
public class consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectUntils.getConnection();
        final Channel channel = connection.createChannel();

        channel.queueDeclare("work",true,false,false,null );
        channel.basicQos( 1 );
        channel.basicConsume("work",false,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("消费者2" +new String(body));
                //手动确认消息 1手动确认消息标识 ， 2 每次只确定一个消息
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });
    }
}
