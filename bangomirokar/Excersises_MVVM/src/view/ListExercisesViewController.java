package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Exercise;
import viewmodel.ListExerciseViewModel;
import viewmodel.SimpleExerciseViewModel;

public class ListExercisesViewController {

    private ViewHandler view;
    private Region root;
    private ListExerciseViewModel viewModel;
    //  FXML

    @FXML TableView<SimpleExerciseViewModel> exercisesTable;
    @FXML TableColumn<SimpleExerciseViewModel,String> numberColumn;
    @FXML TableColumn<SimpleExerciseViewModel, String> TopicColumn;
    @FXML TableColumn<SimpleExerciseViewModel, Boolean> completedColumn;
    @FXML Label errorLabel;

    public void init(ViewHandler view, ListExerciseViewModel viewModel, Region root){
        this.view = view;
        this.viewModel = viewModel;
        this.root = root;

        numberColumn.setCellValueFactory(cellData -> cellData.getValue().getNumberProperty());
        TopicColumn.setCellValueFactory(cellData -> cellData.getValue().getTopicProperty());
        completedColumn.setCellValueFactory(cellData -> cellData.getValue().getCompletedProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());
        exercisesTable.setItems(viewModel.getAll());
    }
    public void reset(){
        viewModel.clear();
        exercisesTable.getSelectionModel().clearSelection();
    }
    public Region getRoot(){return root;}

    @FXML private void removeButton(){
        //  Add functionality for if remove is false
        viewModel.setSelected(exercisesTable.getFocusModel().getFocusedItem());
        viewModel.remove();
        view.openView("manage");
    }
    @FXML private void addEditButton(){
        if(exercisesTable.getFocusModel().getFocusedItem() != null){
            viewModel.setSelected(exercisesTable.getFocusModel().getFocusedItem());
        }
        else{
            viewModel.deselect();
        }
        viewModel.addEdit();
        view.openView("manage");
    }
}
