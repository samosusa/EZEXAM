import java.util.Random;

public class Reader implements Runnable{
    private ReadWriteAccess lock;
    public Reader(ReadWriteAccess lock){
        this.lock = lock;
    }
    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            int randomSeconds = new Random().nextInt(10) + 1;
            try {
                Thread.sleep(randomSeconds * 1000);
            } catch (InterruptedException e) {
                //  ...
            }

            ReadList list = lock.acquireRead();
            list.read();
            lock.releaseRead();
        }
    }
}
