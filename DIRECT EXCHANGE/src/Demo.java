public class Demo {

    public static void main(String args[]) {
        try {
            DirectExchange ex = new DirectExchange();
            ex.createExchangeAndQueue();

            // Publish
            Producer produce = new Producer();
            produce.publish();

            // Consume
            Receiver receive = new Receiver();
            receive.receive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}