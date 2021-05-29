package com.qlu.rjq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 16:26$
 */
public class Provider {
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
        /*
        绑定队列
        * 第一个参数： 队列名称 不纯在自动创建
        * 第二个参数：用来定义队列特性是否要持久化
        * 第三个参数：是否独占队列
        * 第四个参数： 是否在消费完成后自动删除队列
        * 第五个参数：额外附加参数
        * */
        channel.queueDeclare("hello",true,false,false,null );
        //发布消息：交换机名称 ，队列名称，船体消息额外设置，消息内容
        channel.basicPublish("","hello", MessageProperties.PERSISTENT_TEXT_PLAIN,"hello world  /t".getBytes());

        channel.close();
        connection.close();
    }
}
