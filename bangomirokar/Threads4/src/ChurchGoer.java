import javax.swing.plaf.TableHeaderUI;
import java.awt.*;

public class ChurchGoer implements Runnable {

    private ConfessionChair confessionChair;
    public ChurchGoer(ConfessionChair confessionChair){
        this.confessionChair = confessionChair;
    }

    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                confessionChair.EnterConfessionBooth("Kicked a dog");

                for (int j = 0; j < confessionChair.leaveConfessionBooth(); j++) {
                    System.out.println("Ave Maria " + Thread.currentThread().getName());
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
