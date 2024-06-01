import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BirdWatcher implements PropertyChangeListener {

    private String name;
    public BirdWatcher(String name){
        this.name = name;

    }
    public String getName(){return name;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("GodDamn, the bird just did a " + evt.getPropertyName());

    }
}
