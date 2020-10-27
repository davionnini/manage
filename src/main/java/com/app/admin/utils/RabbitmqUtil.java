package com.app.admin.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import com.rabbitmq.client.DeliverCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Component
public class RabbitmqUtil {


    protected static final String HOST = "localhost";

    protected static final Integer PORT = 5672;

    protected static final String USER = "guest";

    protected static final String PASSWORD = "guest";

    protected static final String EXCHANGE_NAME = "test.java.ex";

    protected static final String ROUTER_KEY = "test.java.key";

    protected static final String QUEUE_NAME = "test.java.queue";

    protected Connection connection;

    protected Channel channel;

    protected String routingKey;

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void RabbitmqUtil(){}


    public void connect()
    {
        this.routingKey = routingKey;

        // 创建一个连接工厂 connection factory
        ConnectionFactory factory = new ConnectionFactory();

        // 设置rabbitmq-server服务IP地址、用户名、密码、端口
        factory.setHost(HOST);
        factory.setUsername(USER);
        factory.setPassword(PASSWORD);
        factory.setPort(PORT); //默认端口
        factory.setVirtualHost("/");

        //建立连接
        try {
            // 声明一个连接
            this.connection = factory.newConnection();

            // 声明消息通道
            channel = connection.createChannel();

            /*
             * 声明转发器 - 定义一个交换机 参数1：交换机名称 参数2：交换机类型 参数3：交换机持久性，如果为true则服务器重启时不会丢失
             * 参数4：交换机在不被使用时是否删除 参数5：交换机的其他属性
             */
            channel.exchangeDeclare(EXCHANGE_NAME, "direct", true, false, null);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }



    }


    public void close()
    {

        try {
            channel.close();
            connection.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }

    }


    public void createExchange()
    {}

    public void createQueue(){}

    public void bind(){}

    public void buildConnect()
    {
        ConnectionFactory connect = new ConnectionFactory();
    }

    public void sendMessage(String[] argv)
    {
        String message = argv.length < 1 ? "info: Hello World!" :
                String.join(" ", argv);

        try {
            channel.basicPublish(EXCHANGE_NAME,ROUTER_KEY,null, message.getBytes("UTF-8"));
            logger.info("PDM消息发送成功 -- [ " + EXCHANGE_NAME + " ] - " + routingKey);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void consumer()
    {
        DeliverCallback deliverCallback = (consumerTag, delivery)->{
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        try {
            channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
