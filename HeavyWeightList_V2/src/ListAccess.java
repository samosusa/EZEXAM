import java.awt.*;
import java.util.ArrayList;

public class ListAccess implements ReadWriteAccess{
    private int readers;
    private int writers;
    private int waitingWriters;

    //  V2 solution
    private ArrayList<Thread> allowedReadAccess;
    private ArrayList<Thread> allowedWriteAccess;
    private ReadProxy readProxy;
    private WriteProxy writeProxy;
    public ListAccess(HeavyWeightList list){

        readers = 0;
        writers = 0;
        waitingWriters = 0;
        //////  V2
        allowedReadAccess = new ArrayList<>();
        allowedWriteAccess = new ArrayList<>();
        readProxy = new ReadProxy(list, this);
        writeProxy = new WriteProxy(list, this);
    }
    //  Writers preference
    @Override
    public synchronized ReadList acquireRead() {
        System.out.println(Thread.currentThread().getName() + " is acquiring read; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
        while(writers > 0 || waitingWriters > 0){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting to read; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
                wait();

            }
            catch (InterruptedException e) {}
        }

        ////    V2

        if(!allowedReadAccess.contains(Thread.currentThread())){
            allowedReadAccess.add(Thread.currentThread());
        }

        ///

        readers++;
        System.out.println(Thread.currentThread().getName() + " is reading;" + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
        return readProxy;
    }

    @Override
    public synchronized void releaseRead() {
        readers--;
        System.out.println(Thread.currentThread().getName() + " is releasing read; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);

        ////    V2

        allowedReadAccess.remove(Thread.currentThread());

        ///

        if(readers == 0){
            notify();
        }
    }

    @Override
    public synchronized ReadWriteList acquireWrite() {
        System.out.println(Thread.currentThread().getName() + " is acquiring write; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
        waitingWriters++;
        while(readers > 0 || writers > 0){
            try {
                System.out.println(Thread.currentThread().getName() + " is waiting to write; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
                wait();
            }
            catch (InterruptedException e) {}
        }
        waitingWriters--;

        ////    V2

        if(!allowedWriteAccess.contains(Thread.currentThread())){
            allowedWriteAccess.add(Thread.currentThread());
        }

        ///


        writers++;
        System.out.println(Thread.currentThread().getName() + " is writing;" + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
        return writeProxy;
    }

    @Override
    public synchronized void releaseWrite() {
        writers--;
        System.out.println(Thread.currentThread().getName() + " is releasing write; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);

        allowedWriteAccess.remove(Thread.currentThread());

        notifyAll();
    }
    public boolean hasReadAccess(Thread t){
        for (Thread thread : allowedReadAccess){
            if(thread.equals(t)){
                return true;
            }
        }
        return false;
    }
    public boolean hasWriteAccess(Thread t){
        for (Thread thread : allowedWriteAccess){
            if(thread.equals(t)){
                return true;
            }
        }
        return false;
    }
}
