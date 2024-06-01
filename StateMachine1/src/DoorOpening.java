public class DoorOpening extends DoorState{
    private Thread motor;
    private boolean completed;
    //  On entry - usually the constructor
    public DoorOpening(Door door){
        completed = false;
        //  Creating a new thread that after 5 sec completes the process
        motor = new Thread(() -> {
            try {
                Thread.sleep(5000);
                door.setState(new DoorOpen(door));
                completed = true;
            } catch (InterruptedException e) {
                System.out.println("Motor interrupted the (opening)");
            }
        });
        motor.start();
    }

    @Override public synchronized void compete(Door door){
        if(!completed){
            System.out.println("Motor ended the (opening)");
            door.setState(new DoorOpen(door));
            completed = true;
        }
    }
    //   Upon clicking the elevator button
    @Override
    public synchronized void click(Door door) {
        if(!completed){
            motor.interrupt();
            door.setState(new DoorClosing(door));
            completed = true;
        }
    }
}
