public abstract class Bar {
    protected abstract Drink makeDrink(String name);
    public abstract String[] getDrinkTypes();
    public Drink orderDrink(String name){
        return makeDrink(name);
    }
}
