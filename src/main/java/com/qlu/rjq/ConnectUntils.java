package com.qlu.rjq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * @Author: ruanjq
 * @DateTime: 2021/5/29$ 17:10$
 */
public class ConnectUntils {

    //创建链接的方法
    public static Connection getConnection() {
        try {
            ConnectionFactory connectionFactory = new ConnectionFactory();
            //设置主机
            connectionFactory.setHost("192.168.52.130");
            //设置端口号
            connectionFactory.setPort(5672);
            //设置虚拟主机
            connectionFactory.setVirtualHost("/ems");
            connectionFactory.setUsername("ems");
            connectionFactory.setPassword("123");
            return connectionFactory.newConnection();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null ;
    }
}
