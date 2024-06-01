public class Person implements Runnable {
    PublicToilet toilet;
    private String name;
    public Person(PublicToilet toilet, String name){
        this.toilet = toilet;
        this.name = name;
    }
    public String getName(){return name;}
    @Override
    public void run() {
        try {
            toilet.stepIntoCabin(this);
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        toilet.leaveCabin();
    }
}
