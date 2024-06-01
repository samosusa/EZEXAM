import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;

public class BurgerBar {

    private int numberOfBurgers;
    private int maxNumberOfBurgers;

    public BurgerBar(int maxNumberOfBurgers){
        this.maxNumberOfBurgers = maxNumberOfBurgers;
        numberOfBurgers = 0;
    }
    public synchronized void makeBurger(String employeeName) throws InterruptedException {
        while (numberOfBurgers >= maxNumberOfBurgers){ // if reaches more than max, !!wait!! first
            try{
                System.out.println(employeeName + " is waiting to create burger (" + getNumberOfBurgers() + " left) ," + Thread.currentThread().getName() );
                wait();
            }
            catch (InterruptedException e) { e.printStackTrace(); }

        }
        numberOfBurgers++;
        System.out.println(employeeName + " is making a burger (" + getNumberOfBurgers() + " left) ," + Thread.currentThread().getName() );
        notifyAll();
    }
    public synchronized void eatBurger(String customerName) throws InterruptedException {
        while (numberOfBurgers <= 0 ){   // if it is 0 or bellow !!wait!! for burger to be made
            try{
                System.out.println(customerName + " is waiting for a burger (" + getNumberOfBurgers() + " left) ," + Thread.currentThread().getName() );
                wait();
            }
            catch (InterruptedException e){ e.printStackTrace(); }
        }
        numberOfBurgers--;
        System.out.println(customerName + " is eating a burger (" + getNumberOfBurgers() + " left) ," + Thread.currentThread().getName() );
        notifyAll();

    }
    public synchronized int getNumberOfBurgers(){return numberOfBurgers;}

}
