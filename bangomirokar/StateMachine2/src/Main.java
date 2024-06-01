public class Main {
    public static void main(String[] args) {
        Phone p = new Phone();

        System.out.println("Phone message: " + p.receive());

        p.volumeUp();
        System.out.println("Volume up: " + p.receive());

        p.volumeUp();
        System.out.println("Volume up: " + p.receive());

        p.volumeUp();
        System.out.println("Volume up: " + p.receive());

        p.volumeDown();
        System.out.println("Volume down: " + p.receive());

        p.volumeDown();
        System.out.println("Volume down: " + p.receive());

        p.volumeDown();
        System.out.println("Volume down: " + p.receive());

        p.volumeDown();
        System.out.println("Volume down: " + p.receive());

        p.clickSoundButton();
        System.out.println("Button click: " + p.receive());

        p.clickSoundButton();
        System.out.println("Button click: " + p.receive());

        p.volumeDown();
        System.out.println("Volume down: " + p.receive());

        p.volumeDown();
        System.out.println("Volume down: " + p.receive());

    }
}