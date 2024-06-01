public class CookieEater implements Runnable{

    private CookieJar cookieJar;

    public CookieEater(CookieJar jar){
        cookieJar = jar;
    }
    private void waitSomeTime(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // ...
        }
    }
    @Override
    public void run() {
        while (true){
            cookieJar.eat();
            waitSomeTime();
        }
    }
}
