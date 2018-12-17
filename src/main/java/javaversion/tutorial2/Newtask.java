package javaversion.tutorial2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Newtask {
    public static final String TASK_QUEUE_NAME = "task_queue";

    public static void main(String[] args) throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()
            ){
            channel.queueDeclare(TASK_QUEUE_NAME, true, false, false, null);

            for (int i = 0; i < 100; i++) {
                String message = "Message " + i + ".";
                channel.basicPublish("", TASK_QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes("UTF-8"));
                System.out.println("[x] Sent '" + message + "'");
            }
//            String message = String.join(" ", args);
        }
    }
}
