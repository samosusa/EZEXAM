package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.TemperatureModel;
import model.Temperature;
import viewmodel.ThermometerViewModel;

public class TemperatureViewController {
    @FXML
    private Label outputLabel;
    @FXML
    private TextField filterField;
    @FXML
    private Label filterLabel;

    private ViewHandler viewHandler;

    ThermometerViewModel thermometerViewModel;
    private Region root;

    public TemperatureViewController() {
    }

    public void init(ViewHandler viewHandler, ThermometerViewModel thermometerViewModel, Region root) {
        this.viewHandler = viewHandler;
        this.thermometerViewModel = thermometerViewModel;
        this.root = root;

        // All bindings for  output,   filter: field, label
    }

    public void reset() {
        // empty
    }

    public Region getRoot() {
        return root;
    }

    @FXML
    private void updateButtonPressed() {
        thermometerViewModel.update();
    }

    @FXML
    private void onFilter() {
        thermometerViewModel.filter();
    }
}
