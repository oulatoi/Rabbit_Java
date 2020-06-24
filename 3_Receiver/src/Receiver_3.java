import com.rabbitmq.client.Channel;
        import com.rabbitmq.client.Connection;
        import com.rabbitmq.client.ConnectionFactory;
        import com.rabbitmq.client.DeliverCallback;

public class Receiver_3 {
    private static final String EXCHANGE_NAME = "logs"; //nome dell'exchange

    public static void main(String[] argv) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");//queueName contiene un nome di coda casuale.--DELLA RIGA SOTTO
        String queueName = channel.queueDeclare().getQueue();//queueDeclare () creiamo una coda non durevole, esclusiva,di autocodifica con un nome generato:
        channel.queueBind(queueName, EXCHANGE_NAME, "");//dobbiamo dire allo scambio di inviare messaggi alla nostra coda. Quella relazione tra scambio e una coda è chiamata associazione

        System.out.println(" [*] Waiting for messages. To exit press CTRL+C");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
