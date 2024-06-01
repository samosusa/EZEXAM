public class BurgerBarCustomer implements Runnable{
    private int burgersToEat;
    private String name;
    private BurgerBar burgerBar;
    public BurgerBarCustomer(String name, BurgerBar burgerBar, int burgersToEat){
        this.name = name;
        this.burgerBar = burgerBar;
        this.burgersToEat = burgersToEat;
    }

    @Override
    public void run() {
        for (int i = 0; i < burgersToEat; i++) {
            try {
                burgerBar.eatBurger(name);
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
