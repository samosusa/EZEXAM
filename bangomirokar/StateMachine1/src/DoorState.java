public abstract class DoorState {
    public abstract void click(Door door);

    public void compete(Door door){}
    public void timeout(Door door){}
    public String status(){
        return getClass().getSimpleName();
    }
}
