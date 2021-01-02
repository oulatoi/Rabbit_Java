import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

    private final static String MESSAGE = "Hello Fanout Example";

    public void publish(){
        try{
            Connection conn = RabbitMQConnection.getConnection();
            if(conn != null){
                Channel channel = conn.createChannel();
                channel.basicPublish(FanoutExchange.EXCHANGE_NAME, FanoutExchange.ROUTING_KEY, null, MESSAGE.getBytes());
                System.out.println(" Message Sent '" + MESSAGE + "'");
                channel.close();
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
