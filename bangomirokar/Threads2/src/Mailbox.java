public class Mailbox implements Runnable{
    private long maxFrequency;
    private int count;
    private static long RUNTIME = 10000;
    public Mailbox(int count){
        this.count = count;
    }

    private void waitingForMails() throws InterruptedException {
        Thread.sleep(RUNTIME/count);
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("New mail in your mailbox...");
            try {
                waitingForMails();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
