package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewModel.SimpleVinylViewModel;
import viewModel.VinylListViewModel;


public class VinylListController {

    private ViewHandler view;
    private Region root;
    private VinylListViewModel viewModel;
    //  FXML
    @FXML private TextField inputField;
    @FXML private TableView<SimpleVinylViewModel> vinylTable;
    @FXML private TableColumn<SimpleVinylViewModel ,String> titleColumn;
    @FXML private TableColumn<SimpleVinylViewModel, String> artistColumn;
    @FXML private TableColumn<SimpleVinylViewModel, String> yearColumn;
    @FXML private TableColumn<SimpleVinylViewModel, String> stateColumn;
    @FXML private TableColumn<SimpleVinylViewModel, String> borrowerColumn;
    @FXML private TableColumn<SimpleVinylViewModel, String> reserverColumn;

    @FXML Label errorLabel;
    @FXML Label headerLabel;

    public void init(ViewHandler view, VinylListViewModel viewModel, Region root){
        this.view = view;
        this.viewModel = viewModel;
        this.root = root;

        titleColumn.setCellValueFactory(cellData -> cellData.getValue().titleProperty());
        artistColumn.setCellValueFactory(cellData -> cellData.getValue().artistProperty());
        yearColumn.setCellValueFactory(cellData -> cellData.getValue().yearProperty());
        stateColumn.setCellValueFactory(cellData -> cellData.getValue().stateProperty());
        borrowerColumn.setCellValueFactory(cellData -> cellData.getValue().borrowerProperty());
        reserverColumn.setCellValueFactory(cellData -> cellData.getValue().reserverProperty());

        inputField.textProperty().bindBidirectional(viewModel.nameProperty());
        vinylTable.setItems(viewModel.getAll());

        errorLabel.setText("");
        headerLabel.setText("Vinyl Window");
    }
    public void reset(){
        viewModel.clear();
    }
    public Region getRoot(){return root;}

    @FXML private void onBorrow(){
        try{
            viewModel.setSelected(vinylTable.getFocusModel().getFocusedItem());
            if(viewModel.onBorrow()){
                errorLabel.setText("");
            }
            else{
                errorLabel.setText("No name selected");
            }
        } catch (IllegalStateException e){
            errorLabel.setText(e.getMessage());
        }
    }
    @FXML private void onReserve(){
        try{
            viewModel.setSelected(vinylTable.getFocusModel().getFocusedItem());
            if(viewModel.onReserve()){
                errorLabel.setText("");
            }
            else{
                errorLabel.setText("No name selected");
            }
        } catch (IllegalStateException e){
            errorLabel.setText(e.getMessage());
        }
    }
    @FXML private void onReturn(){
        try{
            viewModel.setSelected(vinylTable.getFocusModel().getFocusedItem());
            if(viewModel.onReturn()){
                errorLabel.setText("");
            }
            else{
                errorLabel.setText("No name selected");
            }
        } catch (IllegalStateException e){
            errorLabel.setText(e.getMessage());
        }
    }
    @FXML private void onAdd(){
        viewModel.onAdd();
    }
    @FXML private void onRemove(){
        viewModel.onRemove();
        System.out.println(vinylTable.getFocusModel().getFocusedItem());
    }
}
