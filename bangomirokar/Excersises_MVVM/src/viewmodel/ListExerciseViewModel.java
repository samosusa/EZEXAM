package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Exercise;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class ListExerciseViewModel implements PropertyChangeListener {
    private ObservableList<SimpleExerciseViewModel> list;
    private ObjectProperty<SimpleExerciseViewModel> selectedExerciseProperty;
    private StringProperty errorLabel;
    private Model model;
    private ViewState viewState;
    public ListExerciseViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;

        errorLabel = new SimpleStringProperty();
        list = FXCollections.observableArrayList();
        selectedExerciseProperty = new SimpleObjectProperty<>();

        loadFromModel();

        model.addListener(this);

    }
    public void clear(){
        errorLabel.set("");
    }

    private void loadFromModel(){   //  Loads all dummy data, rewrites it
        list.clear();
        for (int i = 0; i < model.getAllExercises().size(); i++) {
            list.add(new SimpleExerciseViewModel(model.getAllExercises().get(i)));
        }
    }
    public ObservableList<SimpleExerciseViewModel> getAll() {return list;}
    public void setSelected(SimpleExerciseViewModel exerciseViewModel){
        selectedExerciseProperty.set(exerciseViewModel);
    }
    public void deselect(){ //  Violation of diagram
        selectedExerciseProperty.set(null);
    }
    public StringProperty getErrorProperty(){return errorLabel;}

    public void addEdit(){

        //  Either set viewState to add or edit
        viewState.setRemove(false);


        //  Fix it by catching exception :P
        try{
            viewState.setNumber(selectedExerciseProperty.get().getNumberProperty().get());
            viewState.setTopic(selectedExerciseProperty.get().getTopicProperty().get());

            viewState.setCompleted(selectedExerciseProperty.get().getCompletedProperty().get());
        }
        catch (NullPointerException e){
            viewState.setNumber("TEST");
        }
    }
    public boolean remove(){

        if (selectedExerciseProperty.get() != null) {
            // Open window. view handler
            viewState.setRemove(true);
            viewState.setNumber(selectedExerciseProperty.get().getNumberProperty().get());
            errorLabel.set("");
            return true;
        }
        else {
            errorLabel.set("Nothing selected");
            return false;
        }
    }

    //  Remove from model, then update the observable
    private void removeSimpleExercise(String number){
        for (int i = 0; i < list.size(); i++) {
            if(list.get(i).getNumberProperty().get().equals(number)){
                list.remove(i);
            }
        }
    }

    private void addSimpleExercise(Exercise exercise){
        SimpleExerciseViewModel stuff = new SimpleExerciseViewModel(exercise);
        list.add(stuff);
    }

    //  Unfinished
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        Platform.runLater(() -> {
            String value = evt.getPropertyName();
            System.out.println(value);
            switch (value){
                case "Add" -> {
                    addSimpleExercise((Exercise) evt.getNewValue());
                }
                case "Edit" -> {
                    removeSimpleExercise(evt.getOldValue().toString());
                    addSimpleExercise((Exercise) evt.getNewValue());
                }
                case "Remove" -> {
                    removeSimpleExercise(evt.getOldValue().toString());
                }
            }
        });
    }
}
