public class DoorClosed extends DoorState {

    public DoorClosed(){

    }
    @Override
    public void click(Door door) {
        door.setState(new DoorOpening(door));
    }
}
