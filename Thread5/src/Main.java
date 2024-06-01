public class Main {
    public static void main(String[] args) {

        int numberOfToilets = 2;
        int numberOfPeopleThatNeedToPoop = 20;

        PublicToilet toilet = new ToiletBuilding(numberOfToilets);
        Person[] people = new Person[numberOfPeopleThatNeedToPoop];
        Thread[] threads = new Thread[numberOfPeopleThatNeedToPoop];

        for (int i = 0; i < people.length; i++) {
            people[i] = new Person(toilet, "name" + i);
            threads[i] = new Thread(people[i], "Thread" + (i+1));
            threads[i].start();


        }


    }
}