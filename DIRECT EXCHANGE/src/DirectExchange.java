import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
//import com.rabbitmq.helper.ExchangeType;

public class DirectExchange {

    public static String EXCHANGE_NAME = "direct-exchange";
    public static String QUEUE_NAME_1 = "direct-queue-1";
    public static String QUEUE_NAME_2 = "direct-queue-2";
    public static String QUEUE_NAME_3 = "direct-queue-3";

    public static String ROUTING_KEY_1 = "direct-key-1";
    public static String ROUTING_KEY_2 = "direct-key-2";
    public static String ROUTING_KEY_3 = "direct-key-3";

    public void createExchangeAndQueue(){
        try{
            Connection conn = RabbitMQConnection.getConnection();
            if(conn != null){
                Channel channel = conn.createChannel();
                channel.exchangeDeclare(EXCHANGE_NAME, ExchangeType.DIRECT.getExchangeName(), true);
                // First Queue
                channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
                channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, ROUTING_KEY_1);

                // Second Queue
                channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
                channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, ROUTING_KEY_2);

                // Third Queue
                channel.queueDeclare(QUEUE_NAME_3, true, false, false, null);
                channel.queueBind(QUEUE_NAME_3, EXCHANGE_NAME, ROUTING_KEY_3);

                channel.close();
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}