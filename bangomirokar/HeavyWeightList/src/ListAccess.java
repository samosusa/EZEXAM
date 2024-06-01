public class ListAccess implements ReadWriteAccess{
    private int readers;
    private int writers;
    private int waitingWriters;
    HeavyWeightList list;
    public ListAccess(HeavyWeightList list){
        this.list = list;
        readers = 0;
        writers = 0;
        waitingWriters = 0;
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
        readers++;
        System.out.println(Thread.currentThread().getName() + " is reading;" + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
        return list;
    }

    @Override
    public synchronized void releaseRead() {
        readers--;
        System.out.println(Thread.currentThread().getName() + " is releasing read; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
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
        writers++;
        System.out.println(Thread.currentThread().getName() + " is writing;" + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
        return list;
    }

    @Override
    public synchronized void releaseWrite() {
        writers--;
        System.out.println(Thread.currentThread().getName() + " is releasing write; " + "readers: " + readers + ", " + "writers: " + writers + ", waitingWriters: " + waitingWriters);
        notifyAll();
    }
}
