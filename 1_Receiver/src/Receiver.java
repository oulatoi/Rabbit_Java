import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import  com.rabbitmq.client.DeliverCallback; //classe in più del receiver

public class Receiver {
    private final static String QUEUE_NAME="hello_java";

    public static  void main(String[] args)throws Exception{
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection =factory.newConnection();
        Channel channel =connection.createChannel();

        channel.queueDeclare(QUEUE_NAME,false,false,false,null);
        System.out.println("[*] waiting for message.to exit press CTRL+C" );

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(QUEUE_NAME, true, deliverCallback, consumerTag -> { });
    }
}
