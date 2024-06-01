package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.LogInViewModel;

public class LogInViewController {
    @FXML
    private TextField usernameField, passwordField;
    @FXML
    private Label headerLabel, errorLabel;

    private Region root;
    private ViewHandler viewHandler;
    private LogInViewModel logInViewModel;


    public void init(ViewHandler view, LogInViewModel viewModel, Region root) {
        this.viewHandler = view;
        this.logInViewModel = viewModel;
        this.root = root;

        //  HeaderLabel is just set to the "Create User!" in viewModel
        //headerLabel.textProperty().bind(viewModel.getHeaderProperty());
        usernameField.textProperty().bindBidirectional(viewModel.getUsernameProperty());
        passwordField.textProperty().bindBidirectional(viewModel.getPasswordProperty());
        errorLabel.textProperty().bind(viewModel.getErrorProperty());

        reset();
    }

    public void reset() {
        logInViewModel.clear();
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    public void logInPressed() {
        logInViewModel.createUser();
        if(errorLabel.getText().isEmpty())
            viewHandler.openView("chat");
    }
}
