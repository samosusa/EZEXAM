public class CounterDecrementer implements Runnable{
    private int updates;
    private Counter counter;
    public CounterDecrementer(Counter counter, int updates){
        this.updates = updates;
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < updates; i++) {
            counter.increment();
        }

        System.out.println(counter.getValue());
    }
}
