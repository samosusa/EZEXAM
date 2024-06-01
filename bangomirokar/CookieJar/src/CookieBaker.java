public class CookieBaker implements Runnable {
    private CookieJar cookieJar;

    public CookieBaker(CookieJar jar){
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
            cookieJar.startBaking();
            waitSomeTime();
        }

    }
}
