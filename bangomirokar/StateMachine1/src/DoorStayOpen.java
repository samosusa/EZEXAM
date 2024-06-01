public class DoorStayOpen extends DoorState{


    public DoorStayOpen(){

    }
    @Override
    public void click(Door door) {
        door.setState(new DoorClosing(door));
    }
}
