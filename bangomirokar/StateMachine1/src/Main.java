public class Main {
    public static void main(String[] args) throws InterruptedException {
        Door d = new Door();
//        System.out.println("Start up, the door is " + d.status());  // Closed
//
//        d.click();
//        System.out.println("Clicked, the door is " + d.status());   // Door Opening
//
//        System.out.println("Sleep 2 seconds");
//        Thread.sleep(2000);
//
//        d.click();
//        System.out.println("Clicked, the door is " + d.status());   // Door Closing
//
//        System.out.println("Sleep 6 seconds");
//        Thread.sleep(6000);
//        System.out.println("After 0.5 second the door is " + d.status()); // Closed
//        Thread.sleep(500);
//
//        d.click();
//        System.out.println("Clicked, the door is " + d.status());   // Door Opening
//
//        System.out.println("Sleep 6 seconds");
//        Thread.sleep(6000);
//        System.out.println("After 6 seconds the door is " + d.status());  //  Opened
//
//        d.click();
//        System.out.println("Clicked, the door is " + d.status());   //  Door Closing
//
//        System.out.println("Sleep 1 second");
//        Thread.sleep(1000);
//        System.out.println("After 1 second the door is " + d.status()); // Door opeding

        System.out.println("The door is: " + d.status());

        d.click();
        Thread.sleep(6000);

        System.out.println("Should be opened: " + d.status());


    }
}