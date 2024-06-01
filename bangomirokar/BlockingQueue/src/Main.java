public class Main {
    public static void main(String[] args) {
        BlockingQueue<String> q = new BlockingQueue<>(10);

        Thread thread = new Thread(() -> {
            while (true){

                q.put("one");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //  ...
                }
            }
        });
        thread.start();

        Thread thread1 = new Thread(() -> {
            while (true){

                q.put("two");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //  ...
                }
            }
        });
        thread1.start();

        Thread thread2 = new Thread(() -> {
            while (true){

                q.put("three");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //  ...
                }
            }
        });
        thread2.start();

        Thread thread3 = new Thread(() -> {
            while (true){
                q.take();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    //  ...
                }
            }
        });
        thread3.start();
    }
}