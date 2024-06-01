public class Main {
    public static void main(String[] args) {

        PublicToilet toilet = new ToiletBuilding(5);
        Thread[] people = new Thread[10];
        for (int i = 0; i < people.length; i++) {
            Person person = new Person(toilet);
            Thread personThread = new Thread(person,"person"+i);
            personThread.start();
        }

//        Person person = new Person(toilet);
//        Thread personThread = new Thread(person,"person");
//        personThread.start();

        CleaningPerson cleaningPerson = new CleaningPerson(toilet);
        Thread cleaningPersonThread = new Thread(cleaningPerson,"cleaner");
        cleaningPersonThread.start();
    }
}