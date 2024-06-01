public class DoorOpen extends DoorState{
    private Thread motor;

    public DoorOpen(Door door){

        // Start a thread that sleeps for 5 seconds then calls timeout
        motor = new Thread(() -> {
            try {
                Thread.sleep(5000);
                timeout(door);
            } catch (InterruptedException e) {
                System.out.println("Waiting for close interrupted (Open), start closing");
            }
        });
        motor.start();
    }
    @Override
    public synchronized void timeout(Door door){

        door.setState(new DoorClosing(door));
    }
    @Override
    public synchronized void click(Door door) {

        motor.interrupt();
        door.setState(new DoorClosing(door));
    }
}
