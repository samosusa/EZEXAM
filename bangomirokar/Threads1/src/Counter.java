public class Counter {
    private long value;
    private long max, min;
    public Counter(long min, long max){
        value = 0;
        this.min = min;
        this.max = max;
    }
    public synchronized void increment(){
        while(value < max) {
            value++;
            System.out.println("+ Incrementing: " + value + ", " + Thread.currentThread().getName());
        }
    }
    public synchronized void decrement(){
        while(value > min) {
            value--;
            System.out.println("- Decrementing: " + value + ", " + Thread.currentThread().getName());

        }
    }
    public synchronized long getValue(){return value;}
}
