package PackageCreator;
import Package.*;
public class TextPackageCreator extends PackageCreator{
    @Override
    protected NetworkPackage createPackage(String type, Object value) {

        TextPackage textPack = null;

        if(value.getClass() != TextPackage.class)
            throw new IllegalStateException("Wrong type");
        else
            textPack = new TextPackage(type, value.toString());

        return textPack;
    }
}
