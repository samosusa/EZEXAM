import utility.observer.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class TaskList implements UnnamedPropertyChangeSubject
{
  private ArrayList<Task> tasks;
  private PropertyChangeSupport property;

  public TaskList()
  {
    this.tasks = new ArrayList<Task>();
    this.property = new PropertyChangeSupport(this);
  }

  public synchronized void add(Task task)
  {
    tasks.add(task);
    property.firePropertyChange("ADD", null, task);
  }

  public synchronized Task getAndRemoveNextTask()
  {
    Task task = null;
    if (tasks.size() > 0)
    {
      task = tasks.remove(0);
    }
    property.firePropertyChange("REMOVE", task, null);
    return task;
  }

  public synchronized int size()
  {
    return tasks.size();
  }

  public synchronized String toString()
  {
    return "Tasks=" + tasks;
  }

  @Override public void addListener(PropertyChangeListener listener)
  {
    property.addPropertyChangeListener(listener);
  }

  @Override public void removeListener(PropertyChangeListener listener)
  {
    property.removePropertyChangeListener(listener);
  }
}
