import java.util.ArrayDeque;
import java.util.Queue;

public class ToiletBuilding implements PublicToilet{
    private int numberOfCabins;
    private int availableCabins;
    private int cleaners;


    //  Fair Solution
    private Queue<Thread> queue;

    public ToiletBuilding(int numberOfCabins){
        this.numberOfCabins = numberOfCabins;
        availableCabins = numberOfCabins;

        cleaners = 0;

        queue = new ArrayDeque<>();
    }
    @Override
    public synchronized void stepIntoCabin() {
        queue.offer(Thread.currentThread());    //  Going into the queue
        while (queue.peek() != Thread.currentThread()){ //  If calling thread is not first in the queue then wait until its turn
            try {
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }
        while (availableCabins == 0 || cleaners > 0 ){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting for a cabin! " + "c: " + availableCabins + "/" + numberOfCabins + ", cleaners: " + cleaners + ", q: " + queue.size());
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }
        availableCabins--;
        queue.remove(); //  Removing the first element from queue
        notifyAll();    //  Notifying all because they are in queue anyway. And we check if they are next in the queue as well
        System.out.println(Thread.currentThread().getName() + " is stepping into the cabin; " + "c: " + availableCabins + "/" + numberOfCabins + ", cleaners: " + cleaners + ", q: " + queue.size());
    }
    @Override
    public synchronized void leaveCabin() {
        availableCabins++;
        System.out.println(Thread.currentThread().getName() + " is getting out of a cabin! " + "c: " + availableCabins + "/" + numberOfCabins + ", cleaners: " + cleaners + ", q: " + queue.size());
        notifyAll();    //  Notifying all because the queue is implemented
    }

    @Override
    public synchronized void startCleaning() {
        queue.offer(Thread.currentThread());    //  Getting into the queue
        System.out.println(Thread.currentThread().getName() + " is starting to clean! " + "c: " + availableCabins + "/" + numberOfCabins + ", cleaners: " + cleaners + ", q: " + queue.size());
        while (queue.peek() != Thread.currentThread()){
            try {
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }
        while (availableCabins != 5 || cleaners > 0){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting to clean! " + "c: " + availableCabins + "/" + numberOfCabins + ", cleaners: " + cleaners + ", q: " + queue.size());
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }

        cleaners++;
        queue.remove();
        System.out.println(Thread.currentThread().getName() + " is cleaning the toilets! " + "c: " + availableCabins + "/" + numberOfCabins + ", cleaners: " + cleaners + ", q: " + queue.size());
    }

    @Override
    public synchronized void stopCleaning() {
        cleaners--;
        System.out.println(Thread.currentThread().getName() + " just finished cleaning the toilets! " + "c: " + availableCabins + "/" + numberOfCabins + ", cleaners: " + cleaners + ", q: " + queue.size());
        notifyAll();
    }
}
