import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        //  Monitor class
        CookieJar jar = new CookieJar(16,5,5);

        //  Producer
        CookieBaker baker = new CookieBaker(jar);
        Thread bakerThread = new Thread(baker);
        bakerThread.start();

        //  Consumers
        CookieEater eater1 = new CookieEater(jar);
        CookieEater eater2 = new CookieEater(jar);
        CookieEater eater3 = new CookieEater(jar);

        Thread eaterThread1 = new Thread(eater1);
        Thread eaterThread2 = new Thread(eater2);
        Thread eaterThread3 = new Thread(eater3);

        eaterThread1.start();
        eaterThread2.start();
        eaterThread3.start();

    }
}