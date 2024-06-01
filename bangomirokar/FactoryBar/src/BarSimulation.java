public class BarSimulation {
    public static void main(String[] args) throws InterruptedException {
        Bar bar = new RegularBar();

        Drink drink1 = bar.orderDrink("Carlsberg");
        Drink drink2 = bar.orderDrink("WhitishBrew");
        Drink drink3 = bar.orderDrink("Corona");
        Drink drink4 = bar.orderDrink("RosaliaInBottle");
        Drink drink5 = bar.orderDrink("Tuborg");
        Drink drink6 = bar.orderDrink("CharlyRed");

        System.out.println(drink1.getName() + ", " + drink1.getDescription());
        Thread.sleep(1000);
        System.out.println(drink2.getName() + ", " + drink2.getDescription());
        Thread.sleep(1000);
        System.out.println(drink3.getName() + ", " + drink3.getDescription());
        Thread.sleep(1000);
        System.out.println(drink4.getName() + ", " + drink4.getDescription());
        Thread.sleep(1000);
        System.out.println(drink5.getName() + ", " + drink5.getDescription());
        Thread.sleep(1000);
        System.out.println(drink6.getName() + ", " + drink6.getDescription());
    }
}
