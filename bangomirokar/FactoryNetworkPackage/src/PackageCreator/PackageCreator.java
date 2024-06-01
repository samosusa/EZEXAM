package PackageCreator;
import Package.NetworkPackage;
public abstract class PackageCreator {
    protected abstract NetworkPackage createPackage(String type, Object value);
    public NetworkPackage getPackage(String type, Object value){
        return createPackage(type,value);
    }
}
