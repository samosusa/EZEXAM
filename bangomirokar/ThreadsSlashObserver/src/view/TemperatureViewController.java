package view;

import external.RunnableClock;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.TemperatureModel;
import viewmodel.TempViewModel;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TemperatureViewController
{

  @FXML private TextField textInput;

  @FXML private Label labelTimer;

  @FXML private Label labelOutput;

  private TempViewModel viewModel;
  private Region root;
  private ViewHandler viewHandler;

  public TemperatureViewController()
  {
  }

  public void init(ViewHandler viewHandler,TempViewModel viewModel, Region root)
  {
    this.viewHandler = viewHandler;
    this.viewModel = viewModel;
    this.root = root;
    textInput.textProperty().bindBidirectional(viewModel.textInputProperty());
    labelTimer.textProperty().bind(viewModel.labelTimerProperty());
    labelOutput.textProperty().bind(viewModel.labelOutputProperty());
  }

  public void reset()
  {
    textInput.setText("");
  }

  public Region getRoot()
  {
    return root;
  }

  @FXML private void toCelsius()
  {
    viewModel.toCelsius();
  }

  @FXML private void toFahrenheit()
  {
    viewModel.toFahrenheit();
  }


}