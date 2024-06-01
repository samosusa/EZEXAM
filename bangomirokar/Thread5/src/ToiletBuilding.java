import javax.security.auth.callback.TextOutputCallback;
import java.util.ArrayDeque;
import java.util.Deque;

public class ToiletBuilding implements PublicToilet{
    private int numberOfCabins;
    private int cabinsUsed = 0;

    private Deque<String> queue = new ArrayDeque<>();
    private int queueNum = 0;

    public ToiletBuilding(int number){this.numberOfCabins = number;}


    @Override
    public synchronized void stepIntoCabin(Person person) throws InterruptedException {

        while(numberOfCabins <= cabinsUsed ){
            queue.add(person.getName());
            System.out.println(Thread.currentThread().getName() + " is waiting for toilet. queue = " + queue.getFirst());
            wait();
        }

        cabinsUsed++;
        System.out.println(Thread.currentThread().getName() + " is entering the cabin, cabins used: " + cabinsUsed);
    }

    @Override
    public synchronized void leaveCabin() {
        cabinsUsed--;
        System.out.println(Thread.currentThread().getName() + " is leaving the cabin, cabins that are used " + cabinsUsed);

        notifyAll();
    }
}
