import utility.collection.BoundedArrayQueue;
import utility.collection.QueueADT;

public class    SynchronizedQueue<T> implements Buffer<T>{

    QueueADT<T> q;

    public SynchronizedQueue(int capacity){
        q = new BoundedArrayQueue<>(capacity);
    }
    @Override
    public synchronized void put(T element) {
        q.enqueue(element);
    }

    @Override
    public synchronized T take() {
        return q.dequeue();
    }

    @Override
    public synchronized T look() {
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
