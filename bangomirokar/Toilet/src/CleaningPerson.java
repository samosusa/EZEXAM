import java.util.Random;

public class CleaningPerson implements Runnable{
    private PublicToilet toilet;
    public CleaningPerson(PublicToilet toilet){
        this.toilet = toilet;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int randomSeconds = new Random().nextInt(12) + 5;
            toilet.startCleaning();
            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                //  ...
            }
            toilet.stopCleaning();
        }
    }
}
