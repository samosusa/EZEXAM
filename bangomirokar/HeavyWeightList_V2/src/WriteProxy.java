public class WriteProxy implements ReadWriteList{
    private ListAccess access;
    private HeavyWeightList list;

    public WriteProxy(HeavyWeightList list, ListAccess access){
        this.list = list;
        this.access = access;
    }
    @Override
    public int read() {
        if(access.hasWriteAccess(Thread.currentThread())){
            throw  new IllegalStateException("Thread is not on the list");
        }
        return list.read();
    }

    @Override
    public void write(int value) {
        if(access.hasWriteAccess(Thread.currentThread())){
            throw  new IllegalStateException("Thread is not on the list");
        }
        list.write(value);
    }
}
