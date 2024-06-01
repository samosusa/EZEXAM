package external;

import utility.observer.javaobserver.UnnamedPropertyChangeSubject;
import view.TemperatureViewController;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class RunnableClock implements Runnable, UnnamedPropertyChangeSubject {
    DateTimeFormatter dateTimeFormatter;
    private PropertyChangeSupport property; // Declare this mf


    public RunnableClock(){
        property = new PropertyChangeSupport(this);
    }

    @Override
    public void run() {
        while(true){
            LocalTime time = LocalTime.now();
            dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String timeString = time.format(dateTimeFormatter);


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            //  This separate thread updates the clock
            System.out.println("The Clock: " + timeString);
            property.firePropertyChange("Value",time, timeString);
        }
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }
}
