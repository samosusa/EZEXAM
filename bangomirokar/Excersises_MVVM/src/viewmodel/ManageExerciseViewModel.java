package viewmodel;

import javafx.beans.property.*;
import model.Exercise;
import model.Model;

public class ManageExerciseViewModel {
    private StringProperty errorProperty;
    private StringProperty headerProperty;
    private ObjectProperty<Boolean> completedProperty;
    private StringProperty topicProperty;
    private IntegerProperty numberProperty;
    private IntegerProperty sessionProperty;
    private ObjectProperty<Boolean> editableProperty;

    private Model model;
    private ViewState viewState;
    public ManageExerciseViewModel(Model model, ViewState viewState){
        this.model = model;
        this.viewState = viewState;

        errorProperty = new SimpleStringProperty();
        headerProperty = new SimpleStringProperty();
        completedProperty = new SimpleObjectProperty<>();
        topicProperty = new SimpleStringProperty();
        numberProperty = new SimpleIntegerProperty();
        sessionProperty = new SimpleIntegerProperty();
        editableProperty = new SimpleObjectProperty<>();
    }

    public void reset(){

        //  Very Simple deciding for windows, needs to be improved
        //  Setting variables from ViewState and some styling

        errorProperty.set("");
        if(viewState.getNumber().equals("TEST")){
            headerProperty.set("Add Window");   //  Add window
        }
        else{

            //  Header
            if(!viewState.isRemove()){
                headerProperty.set("Edit Window");      // Edit window
            }
            else{
                headerProperty.set("Remove Window");    //  Remove window
            }

            //  Session and Number
            String[] parts = viewState.getNumber().split("\\.");
            int session = Integer.parseInt(parts[0]);
            int number = Integer.parseInt(parts[1]);
            numberProperty.set(number);
            sessionProperty.set(session);

            //  Topic
            topicProperty.set(viewState.getTopic());

            // Is Completed
            completedProperty.set(viewState.isCompleted());
        }
    }
    public StringProperty getErrorProperty() {return errorProperty;}
    public StringProperty getHeaderProperty(){return headerProperty;}
    public ObjectProperty<Boolean> getCompletedProperty(){return completedProperty;}


    public StringProperty getTopicProperty() {
        return topicProperty;
    }

    public IntegerProperty getNumberProperty() {
        return numberProperty;
    }

    public IntegerProperty getSessionProperty() {
        return sessionProperty;
    }

    public ObjectProperty<Boolean> getEditableProperty() {
        return editableProperty;
    }

    private Exercise createExerciseObject(){
        int number = getSessionProperty().get();
        int exercise = getNumberProperty().get();
        String topic = getTopicProperty().get();

        return new Exercise(number,exercise,topic);
    }
    public boolean accept(){
        //  accept, because I accept how shit this is
        if(numberProperty.get() > 0 && sessionProperty.get() > 0 && topicProperty != null){
            if(!viewState.isRemove()){
                if(viewState.getNumber().equals("TEST")){
                    System.out.println(createExerciseObject());
                    try {
                        model.addExercise(createExerciseObject());
                    }
                    catch (IllegalStateException e) {
                        errorProperty.set(e.getMessage());
                        return false;
                    }
                }
                else{
                    try{
                        model.editExercise(viewState.getNumber(), createExerciseObject());
                    }
                    catch (IllegalStateException e){
                        errorProperty.set(e.getMessage());
                        return false;
                    }
                }
            }
            else{
                model.removeExercise(viewState.getNumber());
            }
            return true;
        }
        return false;
    }
}
