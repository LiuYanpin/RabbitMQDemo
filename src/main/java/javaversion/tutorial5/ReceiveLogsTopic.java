package javaversion.tutorial5;

import com.rabbitmq.client.*;

public class ReceiveLogsTopic {
    public static final String EXCHANGE_NAME = "topic_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.TOPIC);
        String queueName = channel.queueDeclare().getQueue();
        System.out.println("queueName "+ queueName);
        System.out.println("Usage: ReceiveLogsTopic ");

        channel.queueBind(queueName, EXCHANGE_NAME, "kern.*");
        channel.queueBind(queueName, EXCHANGE_NAME, "*.alice");

        System.out.println(" [*] Waiting for messages.");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + delivery.getEnvelope().getRoutingKey() + "':" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> {
        });

    }
}
