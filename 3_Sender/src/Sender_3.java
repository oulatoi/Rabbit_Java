import com.rabbitmq.client.Channel;
import  com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Sender_3 {

    private final static String Exchange_Name="logs";//definizione nome exchange

    public static void main(String[] args) throws Exception{

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection = factory.newConnection();
        Channel channel =connection.createChannel()){
            channel.exchangeDeclare(Exchange_Name,"fanout");//dichiarazione echange e tipo di coda
            for(int i=0;i<10000;i++) {
                String message = "Hello World!" + i;
                channel.basicPublish(Exchange_Name, "", null, message.getBytes("UTF-8"));
                System.out.println("[x] sent'" + message + "'");
            }
        }
    }
}
