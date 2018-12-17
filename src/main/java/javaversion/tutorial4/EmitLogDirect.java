package javaversion.tutorial4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class EmitLogDirect {
    public static final String EXCHANGE_NAME = "direct_logs";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()
        ) {
            channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.DIRECT);

            for (int i = 0; i < 10; i++) {
                String severity = "info";
                String message = "Message---" + i;
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
                System.out.println("[x] Sent '" + severity + "':'" + message + "'");
            }

            for (int i = 10; i < 20; i++) {
                String severity = "warning";
                String message = "Message---" + i;
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
                System.out.println("[x] Sent '" + severity + "':'" + message + "'");
            }

            for (int i = 20; i < 30; i++) {
                String severity = "error";
                String message = "Message---" + i;
                channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes("UTF-8"));
                System.out.println("[x] Sent '" + severity + "':'" + message + "'");
            }

        }
    }

}
