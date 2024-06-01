public class CounterIncrement implements Runnable{
    private int updates;
    private Counter counter;
    public CounterIncrement(Counter counter, int updates){
        this.updates = updates;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < updates; i++) {
            counter.decrement();
        }

        System.out.println(counter.getValue());
    }
}
