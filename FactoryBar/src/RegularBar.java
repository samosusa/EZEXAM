public class RegularBar extends Bar{
    private String[] drinkTypes = {"Carlsberg", "Tuborg", "Corona","CharlyRed","WhitishBrew","RosaliaInBottle"};

    @Override
    protected Drink makeDrink(String name) {
        Drink drink = null;

        switch (name){
            case "Carlsberg" -> {
                drink = new Beer("Carlsberg");
            }
            case "Tuborg" -> {
                drink = new Beer("Tuborg");
            }
            case "Corona" -> {
                drink = new Beer("Corona");
            }
            case "CharlyRed" -> {
                drink = new Wine(name,"red");
            }
            case "WhitishBrew" -> {
                drink = new Wine(name,"white");
            }
            case "RosaliaInBottle" -> {
                drink = new Wine(name,"rose");
            }
        }

        return drink;
    }


    @Override
    public String[] getDrinkTypes() {
        return drinkTypes;
    }
}
