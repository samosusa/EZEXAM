public class Main {
    public static void main(String[] args) {
        ConfessionChair chair = new ConfessionChair();

        ChurchGoer g1 = new ChurchGoer(chair);
        ChurchGoer g2 = new ChurchGoer(chair);
        ChurchGoer g3 = new ChurchGoer(chair);
        ChurchGoer g4 = new ChurchGoer(chair);

        Thread tr1 = new Thread(g1, "Thread1");
        Thread tr2 = new Thread(g2, "Thread2");
        Thread tr3 = new Thread(g3, "Thread3");
        Thread tr4 = new Thread(g4, "Thread4");


        tr1.start();
        tr2.start();
        tr3.start();
        tr4.start();
    }
}