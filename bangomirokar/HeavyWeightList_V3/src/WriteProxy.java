public class WriteProxy implements ReadWriteList{

    private HeavyWeightList list;
    public WriteProxy(HeavyWeightList list){
        this.list = list;
    }
    @Override
    public int read() {
        if (list == null) {
            throw new IllegalStateException("Null HeavyWeightAccessList");
        }
        return list.read();
    }

    @Override
    public void write(int value) {
        if (list == null) {
            throw new IllegalStateException("Null HeavyWeightAccessList");
        }
        list.write(value);
    }
    public void terminate(){
        this.list = null;
    }
}
