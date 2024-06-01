import java.util.ArrayList;

public class CookieJar {
    private int cookieCountWhenToBake;
    private int numberOfCookiesInTheOven;
    private int cookiePlateSize;
    private ArrayList<Cookie> cookies;
    public CookieJar(int maxCookies, int cookieCountWhenToBake, int numberOfCookiesInTheOven){
        cookies = new ArrayList<>(maxCookies);
        this.cookiePlateSize = maxCookies;
        this.cookieCountWhenToBake = cookieCountWhenToBake;
        this.numberOfCookiesInTheOven = numberOfCookiesInTheOven;
    }

    public synchronized void startBaking(){
        while(cookies.size() > cookieCountWhenToBake-1){
            try {
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }
        //  Calling the method here
        System.out.println("Baker started baking " + finishedBaking() + " cookies");
        notify();
    }
    public synchronized int finishedBaking(){
        while (cookies.size() < cookiePlateSize) {
            cookies.add(new Cookie("Sweet"));
        }
        return numberOfCookiesInTheOven;
    }
    public synchronized void eat(){
        while (cookies.size() <= 0){
            try {
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }
        System.out.println("Somebody has eaten a cookie! number of cookies is " + cookies.size());
        cookies.remove(0);
        notify();
    }

}
