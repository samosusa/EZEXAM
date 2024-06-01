import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TaskList implements UnnamedPropertyChangeSubject {
    private ArrayList<Task> tasks;
    private PropertyChangeSupport property;
    public TaskList(){
        tasks = new ArrayList<>();
        property = new PropertyChangeSupport(this);
    }
    public synchronized void addTask(Task task){
        tasks.add(task);
        property.firePropertyChange("ADD", null, task);
    }
    public synchronized Task getAndRemoveNextTask(){
        if (!tasks.isEmpty())
        {
            property.firePropertyChange("REMOVE",null,tasks.get(0));
            return tasks.remove(0);
        }
        return null;

    }
    public synchronized int size(){
        return tasks.size();
    }

    @Override
    public synchronized String toString() {
        String s = "";
        for(Task task : tasks){
            s += task + "\n";
        }
        return s;
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
