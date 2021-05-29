package com.qlu.rjq.workQuery;

import com.qlu.rjq.ConnectUntils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 16:26$
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectUntils.getConnection();
        Channel channel = connection.createChannel();

        /*
        绑定队列
        * 第一个参数： 队列名称 不纯在自动创建
        * 第二个参数：用来定义队列特性是否要持久化
        * 第三个参数：是否独占队列
        * 第四个参数： 是否在消费完成后自动删除队列
        * 第五个参数：额外附加参数
        * */
        channel.queueDeclare("work",true,false,false,null );
        //发布消息：交换机名称 ，队列名称，船体消息额外设置，消息内容
        for (int i=0;i<100;i++){
            channel.basicPublish("","work", MessageProperties.PERSISTENT_TEXT_PLAIN,(" world query /t"+i).getBytes());

        }
    }
}
