package com.qlu.rjq.fanout;

import com.qlu.rjq.ConnectUntils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 18:15$
 */
public class Provider {
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = ConnectUntils.getConnection();
        Channel channel = connection.createChannel();
        /*
        * 参数1代表交换机名字
        * 2，交换机类型  fanout 广播类型
        * */
        channel.exchangeDeclare("logs","fanout");
        channel.basicPublish("logs","",null,"fanout type message".getBytes());

        channel.close();
        connection.close();
    }
}
