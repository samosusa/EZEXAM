public class ReadProxy implements ReadList{
    private HeavyWeightList list;
    public ReadProxy(HeavyWeightList list){
        this.list = list;
    }
    @Override
    public int read() {
        if (list == null) {
            throw new IllegalStateException("Null HeavyWeightAccessList");
        }
        return list.read();
    }
    public void terminate(){
        this.list = null;
    }
}
