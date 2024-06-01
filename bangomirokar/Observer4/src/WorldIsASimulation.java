public class WorldIsASimulation {
    public static void main(String[] args) throws InterruptedException {


        Bird b1 = new Bird("Ghetto Bird");

        BirdWatcher w1 = new BirdWatcher("Tango");
        BlindBirdWatcher w2 = new BlindBirdWatcher("Foxtrot");
        DeafBirdWatcher w3 = new DeafBirdWatcher("Delta");

        //b1.addListener("Normal", w1);
        b1.addListener("Blind", w2);
        b1.addListener("Deaf", w3);

        System.out.println(b1.Squeek());
        Thread.sleep(2500);
        System.out.println("----------------------------------");
        System.out.println(b1.Move());
        Thread.sleep(2500);
        System.out.println("----------------------------------");
        System.out.println(b1.Move());
        Thread.sleep(2500);
        System.out.println("----------------------------------");
        System.out.println(b1.Squeek());
        Thread.sleep(2500);
        System.out.println("----------------------------------");
        System.out.println(b1.Squeek());
        Thread.sleep(2500);
        System.out.println("----------------------------------");
        System.out.println(b1.Squeek());
        Thread.sleep(2500);
        System.out.println("----------------------------------");
        System.out.println(b1.Move());
        Thread.sleep(2500);
    }
}
