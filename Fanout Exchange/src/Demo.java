public class Demo {

    public static void main(String args[]){
        try{
            FanoutExchange ex = new FanoutExchange();
            ex.createExchangeAndQueue();

            // Publish
            Producer produce = new Producer();
            produce.publish();

            // Consume
            Receiver receive = new Receiver();
            receive.receive();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
