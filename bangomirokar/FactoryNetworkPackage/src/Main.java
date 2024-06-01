
import Package.*;
import PackageCreator.*;
public class Main {
    public static void main(String[] args) {

        PackageCreator taskCreator = new TaskPackageCreator();
        PackageCreator textCreator = new TextPackageCreator();

        System.out.println(taskCreator.getPackage("Text",new Task("Task1",420)));

    }
}
