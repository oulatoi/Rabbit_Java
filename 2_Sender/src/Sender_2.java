import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class Sender_2 {
    private static final String TASK_QUEUE_NAME ="task_queue_java";
    public static void main(String[] args) throws Exception
    {
        ConnectionFactory factory= new ConnectionFactory();
        factory.setHost("localhost");
        try(Connection connection=factory.newConnection();
            Channel channel= connection.createChannel()){
            channel.queueDeclare(TASK_QUEUE_NAME,true,false,false,null);
            for(int i=0;i<10000;i++)
            {

                String message="Hello World!";

                channel.basicPublish("",TASK_QUEUE_NAME,
                        MessageProperties.PERSISTENT_TEXT_PLAIN,
                        message.getBytes("UTF-8"));
                System.out.println("[x] Sent '"+ message+"'");
            }
        }

    }
}
