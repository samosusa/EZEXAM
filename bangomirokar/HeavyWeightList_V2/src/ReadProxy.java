public class ReadProxy implements ReadList{
    private ListAccess access;
    private HeavyWeightList list;
    public ReadProxy(HeavyWeightList list, ListAccess access){
        this.list = list;
        this.access = access;
    }
    @Override
    public int read() {
        if(access.hasReadAccess(Thread.currentThread())){
            throw new IllegalStateException("Thread is not on the list");
        }
        return list.read();
    }
}
