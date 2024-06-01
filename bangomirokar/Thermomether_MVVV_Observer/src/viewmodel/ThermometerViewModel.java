package viewmodel;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.TemperatureModel;

public class ThermometerViewModel {

    private TemperatureModel model;

    private StringProperty outputLabel;
    private SimpleFloatProperty filterField;
    private StringProperty filterLabel;

    public ThermometerViewModel(TemperatureModel model){
        this.model = model;
        //  May Add starting values
        this.outputLabel = new SimpleStringProperty();
        this.filterLabel = new SimpleStringProperty();
        this.filterField = new SimpleFloatProperty();
    }

    public void update(){
        //   To add a thing, not completed
    }

    public void filter(){
        //   To add a thing, not completed
    }

    public String getOutputLabel() {
        return outputLabel.get();
    }

    public StringProperty outputLabelProperty() {
        return outputLabel;
    }

    public float getFilterField() {
        return filterField.get();
    }

    public SimpleFloatProperty filterFieldProperty() {
        return filterField;
    }

    public String getFilterLabel() {
        return filterLabel.get();
    }

    public StringProperty filterLabelProperty() {
        return filterLabel;
    }
}
