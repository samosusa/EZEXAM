public class Main {
    public static void main(String[] args) {
        BurgerBar burgerBar = new BurgerBar(50);

        BurgerBarEmployee emp1 = new BurgerBarEmployee("Alpha", burgerBar);
        BurgerBarEmployee emp2 = new BurgerBarEmployee("Bravo", burgerBar);

        BurgerBarCustomer cus1 = new BurgerBarCustomer("Charlie",burgerBar, 20);
        BurgerBarCustomer cus2 = new BurgerBarCustomer("Delta",burgerBar, 12);
        BurgerBarCustomer cus3 = new BurgerBarCustomer("Echo",burgerBar, 10);
        BurgerBarCustomer cus4 = new BurgerBarCustomer("Foxtrot",burgerBar, 5);

        Thread tr1 = new Thread(emp1, "Thread1 emp");
        Thread tr2 = new Thread(emp2, "Thread2 emp");

        Thread tr3 = new Thread(cus1, "Thread3 cust");
        Thread tr4 = new Thread(cus2, "Thread4 cust");
        Thread tr5 = new Thread(cus3, "Thread5 cust");
        Thread tr6 = new Thread(cus4, "Thread6 cust");

        tr1.start();
        tr2.start();
        tr3.start();
        tr4.start();
        tr5.start();
        tr6.start();
    }
}