public class DoorClosing extends DoorState{

    private Thread motor;
    private boolean completed;
    public DoorClosing(Door door){
        completed = false;
        motor = new Thread(() -> {
            try {
                Thread.sleep(5000);
                compete(door);
            } catch (InterruptedException e) {
                System.out.println("Closing interrupted ");
            }

        });
        motor.start();
    }

    @Override public synchronized void compete(Door door){
        if(!completed){
            System.out.println("Motor ended (closing)");
            door.setState(new DoorClosed());
            completed = true;
        }
    }

    @Override
    public synchronized void click(Door door) {
        if(!completed){
            motor.interrupt();
            door.setState(new DoorOpening(door));
            completed = true;
        }
    }
}
