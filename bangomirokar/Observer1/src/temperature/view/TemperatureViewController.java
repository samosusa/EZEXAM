package temperature.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import temperature.mediator.TemperatureModel;
import temperature.model.Temperature;

public class TemperatureViewController
{
   @FXML private Label outputLabel;
   @FXML private TextField filterField;
   @FXML private Label filterLabel;

   private ViewHandler viewHandler;
   private TemperatureModel model;
   private Region root;
   private String thermometerId;

   public TemperatureViewController()
   {
   }

   public void init(ViewHandler viewHandler, TemperatureModel model, Region root)
   {
      this.viewHandler = viewHandler;
      this.model = model;
      this.root = root;
      thermometerId = null;
   }

   public void reset()
   {
      // empty
   }

   public Region getRoot()
   {
      return root;
   }

   @FXML private void updateButtonPressed()
   {
      Temperature t = model.getLastInsertedTemperature(thermometerId);
      if (t != null)
      {
         outputLabel.setText(t.toString());
      }
      else
      {
         outputLabel.setText("No data");
      }
   }

   @FXML private void onFilter()
   {
      String input = filterField.getText();
      if (input == null || input.isEmpty() || input.equalsIgnoreCase("All"))
      {
         input = null;
      }
      thermometerId = input;
      if (thermometerId == null)
      {
         filterLabel.setText("All");
      }
      else
      {
         filterLabel.setText(thermometerId);
      }
      filterField.setText(null);
      updateButtonPressed();
   }
}
