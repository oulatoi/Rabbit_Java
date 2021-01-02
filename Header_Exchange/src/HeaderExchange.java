import java.util.HashMap;
import java.util.Map;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
//import com.rabbitmq.helper.ExchangeType;

public class HeaderExchange {

    public static String EXCHANGE_NAME = "header-exchange";
    public static String QUEUE_NAME_1 = "header-queue-1";
    public static String QUEUE_NAME_2 = "header-queue-2";
    public static String QUEUE_NAME_3 = "header-queue-3";

    public static String ROUTING_KEY = "";

    public void createExchangeAndQueue(){
        Map<String,Object> map = null;
        try{
            Connection conn = RabbitMQConnection.getConnection();
            if(conn != null){
                Channel channel = conn.createChannel();

                channel.exchangeDeclare(EXCHANGE_NAME, ExchangeType.HEADER.getExchangeName(), true);
                // First Queue
                map = new HashMap<String,Object>();
                map.put("x-match","any");
                map.put("First","A");
                map.put("Fourth","D");
                channel.queueDeclare(QUEUE_NAME_1, true, false, false, null);
                channel.queueBind(QUEUE_NAME_1, EXCHANGE_NAME, ROUTING_KEY ,map);

                // Second Queue
                map = new HashMap<String,Object>();
                map.put("x-match","any");
                map.put("Fourth","D");
                map.put("Third","C");
                channel.queueDeclare(QUEUE_NAME_2, true, false, false, null);
                channel.queueBind(QUEUE_NAME_2, EXCHANGE_NAME, ROUTING_KEY ,map);

                // Third Queue
                map = new HashMap<String,Object>();
                map.put("x-match","all");
                map.put("First","A");
                map.put("Third","C");
                channel.queueDeclare(QUEUE_NAME_3, true, false, false, null);
                channel.queueBind(QUEUE_NAME_3, EXCHANGE_NAME, ROUTING_KEY ,map);

                channel.close();
                conn.close();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}