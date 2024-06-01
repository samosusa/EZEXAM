import java.util.Random;

public class Person implements Runnable{
    private PublicToilet toilet;
    public Person(PublicToilet toilet){
        this.toilet = toilet;
    }


    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            int randomSeconds = new Random().nextInt(10) + 2;
            toilet.stepIntoCabin();
            try {
                Thread.sleep(randomSeconds * 1000);
            } catch (InterruptedException e) {
                //  ...
            }
            toilet.leaveCabin();
        }
    }
}
