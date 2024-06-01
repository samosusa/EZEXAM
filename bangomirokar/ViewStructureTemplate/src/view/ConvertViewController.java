package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import viewmodel.ConvertViewModel;
import javafx.scene.layout.Region;


public class ConvertViewController {
    @FXML private TextField requestField;
    @FXML private TextField replyField;
    @FXML private Label errorLabel;
    private Region root;
    private ViewHandler view;
    private ConvertViewModel viewModel;

    public void init(Region root, ViewHandler view, ConvertViewModel viewModel){
        this.root = root;
        this.view = view;
        this.viewModel = viewModel;

        requestField.textProperty().bindBidirectional(viewModel.requestProperty());
        replyField.textProperty().bind(viewModel.replyProperty());
        errorLabel.textProperty().bind(viewModel.errorProperty());
    }

    public void reset(){
        viewModel.clear();
    }
    public Region getRoot(){return root;}
    @FXML private void onSubmit(){
        viewModel.convert();
    }
    @FXML private void onEnter(){
        //  Make logic of going downwards after enter
        viewModel.convert();
    }

}
