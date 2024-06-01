public class BurgerBarEmployee implements Runnable{

    private String name;
    private BurgerBar burgerBar;
    public BurgerBarEmployee(String name, BurgerBar burgerBar){
        this.burgerBar = burgerBar;
        this.name = name;
    }

    @Override
    public void run() {
        while (true){
            try {
                burgerBar.makeBurger(name);
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
