import utility.collection.BoundedArrayQueue;
import utility.collection.QueueADT;

public class BlockingQueue<T> implements Buffer<T>{




    QueueADT<T> q;

    public BlockingQueue(int capacity){
        q = new BoundedArrayQueue<>(capacity);
    }
    @Override
    public synchronized void put(T element) {
        //  Add exception if element = null

        while(isFull()){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting because stack is full");
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }

        System.out.println(Thread.currentThread().getName() + " is putting stuff in");
        q.enqueue(element);
        notifyAll();
    }

    @Override
    public synchronized T take() {

        while(isEmpty()){
            try {
                System.out.println(Thread.currentThread().getName() + " empty stack, thread is waiting");
                wait();
            } catch (InterruptedException e) {
                //  ...
            }
        }
        notify();
        System.out.println(Thread.currentThread().getName() + " is taking stuff out");
        return q.dequeue();
    }

    @Override
    public synchronized T look() {
        if(isEmpty())
            return null;
        else
            return q.first();
    }

    @Override
    public synchronized boolean isEmpty() {
        return q.isEmpty();
    }

    @Override
    public synchronized boolean isFull() {
        return q.isFull();
    }

    @Override
    public synchronized int size() {
        return q.size();
    }
}
