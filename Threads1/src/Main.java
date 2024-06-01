public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter(2,12);

        CounterIncrement c1 = new CounterIncrement(counter, 200);
        CounterIncrement c2 = new CounterIncrement(counter, 200);
        CounterDecrementer c3 = new CounterDecrementer(counter, 200);
        CounterDecrementer c4 = new CounterDecrementer(counter, 200);

        Thread th1 = new Thread(c1, "tr1");
        Thread th2 = new Thread(c2, "tr2");
        Thread th3 = new Thread(c3, "tr3");
        Thread th4 = new Thread(c4, "tr4");

        th4.start();
        th3.start();
        th2.start();
        th1.start();

    }
}