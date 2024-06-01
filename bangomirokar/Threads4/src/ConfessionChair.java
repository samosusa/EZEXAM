import java.util.Random;

public class ConfessionChair {
    private String sin;
    private Random random = new Random();
    public synchronized void EnterConfessionBooth(String sin) throws InterruptedException {


        this.sin = sin;
        System.out.println(Thread.currentThread().getName() + " is confessing a " + sin);
        wait();
    }
    public synchronized int leaveConfessionBooth() throws InterruptedException {

        int penaltyNum = random.nextInt(1,20);
        System.out.println(Thread.currentThread().getName() + " is leaving the booth and getting penalty of: " + penaltyNum);

        return penaltyNum;
    }

}
