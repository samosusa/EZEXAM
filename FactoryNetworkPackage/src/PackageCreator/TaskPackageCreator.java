package PackageCreator;
import Package.*;
public class TaskPackageCreator extends PackageCreator{
    @Override
    protected NetworkPackage createPackage(String type, Object value) {
        TaskPackage taskPack = null;

        if(value.getClass() != Task.class)
            throw new IllegalStateException("Wrong value type");
        else
            taskPack = new TaskPackage(type, (Task) value);

        return taskPack;
    }
}
