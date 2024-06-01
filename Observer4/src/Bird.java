import utility.observer.javaobserver.NamedPropertyChangeSubject;
import utility.observer.javaobserver.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class Bird implements NamedPropertyChangeSubject {

    private String name;
    private PropertyChangeSupport property;

    private int[] position = new int[2];

    public Bird(String name){
        this.name = name;
        property = new PropertyChangeSupport(this);
        Move();
    }

    public String getName(){return name;}

    //  Bird can: Move its wings, Sing a song

    public String Move(){
        position[0] = (int) (Math.random() * 10);
        position[1] = (int) (Math.random() * 10);
        property.firePropertyChange("Move", null, getName());
        return "JEBAL TO PES IDEM DOPICE (moved)";
    }
    public String Squeek(){
        property.firePropertyChange("Squeek", null, getName());
        return "CO JE TY KOKOTKO (squeked)";
    }

    public String LocationCheck(){return "(" + position[0] + ", " + position[1] + ")";}

    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.removePropertyChangeListener(listener);
    }
}
