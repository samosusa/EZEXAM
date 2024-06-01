package Package;

public class NetworkPackage {
    private String type;
    public NetworkPackage(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }

    @Override
    public String toString(){
        return "NetworkPackage t: " + type;
    }
}
