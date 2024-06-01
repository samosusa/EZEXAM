package view;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import javafx.util.StringConverter;
import utility.IntStringConverter;
import viewmodel.ManageExerciseViewModel;


public class ManageExerciseViewController {
    private ManageExerciseViewModel viewModel;
    private ViewHandler view;
    private Region root;
    //  FXML
    @FXML private Label headerLabel;
    @FXML private TextField sessionField;
    @FXML private TextField numberField;
    @FXML private TextField topicField;
    @FXML private RadioButton completedRadiobutton;
    @FXML private Button submitButton;
    @FXML private Label errorLabel;
    public void init(ViewHandler view, ManageExerciseViewModel viewModel, Region root){
        this.view = view;
        this.root = root;
        this.viewModel = viewModel;

        headerLabel.textProperty().bind(viewModel.getHeaderProperty());
        topicField.textProperty().bindBidirectional(viewModel.getTopicProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());

        //  Idk how to bind radio Button

        Bindings.bindBidirectional(sessionField.textProperty(),viewModel.getSessionProperty(), new IntStringConverter() {
            @Override
            public String toString(Number number) {
                if (number == null || number.intValue() == 0){
                    return "";
                }
                else{
                    return number.toString();
                }
            }

            @Override
            public Number fromString(String s) {
                try{
                    return Integer.parseInt(s);
                }
                catch(Exception e) {return 0;}
            }
        });
        Bindings.bindBidirectional(numberField.textProperty(),viewModel.getNumberProperty(), new IntStringConverter() {
            @Override
            public String toString(Number number) {
                if (number == null || number.intValue() == 0){
                    return "";
                }
                else{
                    return number.toString();
                }
            }

            @Override
            public Number fromString(String s) {
                try{
                    return Integer.parseInt(s);
                }
                catch(Exception e) {return 0;}
            }
        });


        reset();
    }
    public void reset(){
        //  Disable buttons somehow
        viewModel.reset();
    }
    public Region getRoot(){return root;}

    @FXML
    private void submitButton(){

        if(viewModel.accept()){
            view.openView("list");
        }
    }
    @FXML
    private void cancelButton(){
        view.openView("list");
    }
    @FXML
    private void onEnter(ActionEvent event){

    }


}
