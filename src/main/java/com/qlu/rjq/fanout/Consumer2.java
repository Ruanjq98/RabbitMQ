package com.qlu.rjq.fanout;

import com.qlu.rjq.ConnectUntils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 18:24$
 */
public class Consumer2 {
    public static void main(String[] args) throws IOException {
        Connection connection = ConnectUntils.getConnection();
        Channel channel = connection.createChannel();

        //交换机绑定
        channel.exchangeDeclare("logs","fanout");
        //临时队列
        String queue = channel.queueDeclare().getQueue();
        channel.queueBind(queue,"logs","");

        //消费消息
        channel.basicConsume(queue,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费者2" +new String(body));
            }
        });
    }
}
