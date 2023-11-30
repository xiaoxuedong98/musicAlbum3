package com.cs6650.album.server;


import com.cs6650.album.server.bean.LikeRequest;
import com.google.gson.Gson;
import com.rabbitmq.client.*;
import org.apache.log4j.Logger;

public class QueueConsumer implements Runnable {
    private static final Logger logger = Logger.getLogger(QueueConsumer.class);
    private final Channel channel;
    private AlbumsService albumsService = AlbumsService.getInstance();

    Gson gson = new Gson();

    public QueueConsumer(Channel channel) {
        this.channel = channel;
    }

    @Override
    public void run() {
        try {
            Consumer consumer = new DefaultConsumer(channel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                    String message = new String(body);
                    // TODO: Handle message
                    try {
                        logger.info("Received message from RabbitMQ: " + message);
                        LikeRequest likeRequest = gson.fromJson(message, LikeRequest.class);
                        logger.info("Received likeRequest: " + likeRequest);
                        albumsService.insertLikeDislike(likeRequest);
                        logger.info("Inserted like/dislike for album ID " + likeRequest.getAlbumID());
                    } catch (Exception e) {
                        logger.error("Failed to insert like/dislike for album ID " + message, e);
                    }
                }
            };
            channel.basicConsume("musicAlbum", true, consumer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

