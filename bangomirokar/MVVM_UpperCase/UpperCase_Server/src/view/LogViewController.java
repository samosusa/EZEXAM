package view;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import viewmodel.LogViewModel;
import javafx.scene.layout.Region;

public class LogViewController {

    @FXML TextField inputField;
    @FXML private ListView<String> logList;

    private Region root;
    private LogViewModel viewModel;
    private ViewHandler view;
    public void init(ViewHandler view, LogViewModel viewModel, Region root){
        this.view = view;
        this.viewModel = viewModel;
        this.root = root;

        inputField.textProperty().bindBidirectional(viewModel.getTextProperty());

        logList.setItems(viewModel.getLogs());
    }
    public Region getRoot(){
        return root;
    }
    public void reset(){

    }

    @FXML private void onEnter(ActionEvent actionEvent) {
        //  Broadcast some message to all clients connected?
        viewModel.enterPressed();

    }
}
