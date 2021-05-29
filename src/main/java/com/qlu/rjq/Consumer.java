package com.qlu.rjq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 16:54$
 */
public class Consumer {
    public static void main(String[] args) throws IOException, TimeoutException {
        //创建mq链接工厂
        ConnectionFactory factory = new ConnectionFactory();
        //设置主机
        factory.setHost("192.168.52.130");
        //设置端口号
        factory.setPort(5672);
        //设置虚拟主机
        factory.setVirtualHost("/ems");
        factory.setUsername("ems");
        factory.setPassword("123");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("hello",true,false,false,null );
        /*
        * 参数： 消费队列名称
        * 开始消息自动确认
        * 消费时的回调参数
        *
        * */
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            //回调方法
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.printf(new String(body));
            }
        });
    }

}
