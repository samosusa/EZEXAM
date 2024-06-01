public class Wine extends Drink{
    public static String RED = "red";
    public static String WHITE = "white";
    public static String ROSE = "rose";
    private String type;
    public Wine(String name, String type) {
        super(name,"");
        this.type = type;
        switch (type){
            case "red":
                super.setDescription("Slightly cold");
                break;
            case "white":
                super.setDescription("Cold from fridge");
                break;
            case "rose":
                super.setDescription("Its pink and slightly cold");
                break;
        }
    }

}
