import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class BlindBirdWatcher implements PropertyChangeListener {

    private String name;

    public BlindBirdWatcher(String name){
        this.name = name;
    }
    public String getName(){return name;}

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("Squeek")){
            System.out.println("Did u hear that? I dont see shit but I head a bird");
        }
    }
}
