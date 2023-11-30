package com.cs6650.album.server;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.apache.log4j.Logger;

public class RabbitMQService {
    private Connection connection;
    private Channel channel;
    private static final Logger logger = Logger.getLogger(RabbitMQService.class);

    public void init() throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("b-e56c5d1f-c2bb-4d9a-bf1f-3326cc5a4b16.mq.us-west-2.amazonaws.com"); // Set RabbitMQ server address
        factory.setPort(5671);
        // Allows client to establish a connection over TLS
        factory.useSslProtocol();

        factory.setUsername("album"); // Set username
        factory.setPassword("albumalbumalbum"); // Set password
        // Create connection and channel
        connection = factory.newConnection();
        channel = connection.createChannel();
        // Declare queue
        channel.queueDeclare("musicAlbum", false, false, false, null);
        logger.info("RabbitMQ service initialized");
    }

    public Channel getChannel() {
        return channel;
    }

    public void close() throws Exception {
        if (channel != null && channel.isOpen()) {
            channel.close();
        }
        if (connection != null && connection.isOpen()) {
            connection.close();
        }
    }
}

