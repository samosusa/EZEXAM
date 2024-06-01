import java.util.Random;

public class Writer implements Runnable{
    private ReadWriteAccess lock;
    public Writer(ReadWriteAccess lock){
        this.lock = lock;
    }
    @Override
    public void run() {

        for (int i = 0; i < 3; i++) {
            int randomSeconds = new Random().nextInt(10) + 5;
            try {
                Thread.sleep(randomSeconds * 1000);
            } catch (InterruptedException e) {
                //  ...
            }
            ReadWriteList list = lock.acquireWrite();
            list.write(100);
            lock.releaseWrite(list);


        }

    }
}
