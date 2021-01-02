import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class Producer {

    private final static String MESSAGE_1 = "First Header Message Example";
    private final static String MESSAGE_2 = "Second Header Message Example";
    private final static String MESSAGE_3 = "Third Header Message Example";

    public void publish(){
        Map<String,Object> map = null;
        BasicProperties props = null;
        try{
            Connection conn = RabbitMQConnection.getConnection();
            if(conn != null){
                Channel channel = conn.createChannel();

                // First message
                props = new BasicProperties();
                map = new HashMap<String,Object>();
                map.put("First","A");
                map.put("Fourth","D");
                props = props.builder().headers(map).build();
                channel.basicPublish(HeaderExchange.EXCHANGE_NAME, HeaderExchange.ROUTING_KEY, props, MESSAGE_1.getBytes());
                System.out.println(" Message Sent '" + MESSAGE_1 + "'");

                // Second message
                props = new BasicProperties();
                map = new HashMap<String,Object>();
                map.put("Third","C");
                props = props.builder().headers(map).build();
                channel.basicPublish(HeaderExchange.EXCHANGE_NAME, HeaderExchange.ROUTING_KEY, props, MESSAGE_2.getBytes());
                System.out.println(" Message Sent '" + MESSAGE_2 + "'");

                // Third message
                map = new HashMap<String,Object>();
                props = new BasicProperties();
                map.put("First","A");
                map.put("Third","C");
                props = props.builder().headers(map).build();
                channel.basicPublish(HeaderExchange.EXCHANGE_NAME, HeaderExchange.ROUTING_KEY, props, MESSAGE_3.getBytes());
                System.out.println(" Message Sent '" + MESSAGE_3 + "'");

                channel.close();
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
