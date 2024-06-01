package Package;

public class TaskPackage extends NetworkPackage{
    private Task task;
    public TaskPackage(String type, Task task) {
        super(type);
        this.task = task;
    }
    public Task getTask(){
        return this.task;
    }
    @Override
    public String toString(){
        return "TaskPackage t:" + super.getType() + ", task: " + task;
    }
}
