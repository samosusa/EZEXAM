import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class DeafBirdWatcher implements PropertyChangeListener {

    private String name;
    public DeafBirdWatcher(String name){
        this.name = name;
    }
    public String getName(){return name;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Move")){
            System.out.println("Bird is moving again, god damn... aint no way");
        }
    }
}
