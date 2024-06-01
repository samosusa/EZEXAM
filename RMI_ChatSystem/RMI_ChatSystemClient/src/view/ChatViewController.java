package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import viewmodel.ChatViewModel;

public class ChatViewController
{
  @FXML private Label headerLabel;
  @FXML private TextArea chatList;
  @FXML private TextField inputField;
  @FXML private Label errorLabel;
  private Region root;
  private ViewHandler viewHandler;
  private ChatViewModel chatViewModel;

  //initializations and bindings
  public void init(ViewHandler viewHandler,
      ChatViewModel chatViewModel, Region root)
  {
    this.root = root;
    this.viewHandler = viewHandler;
    this.chatViewModel=chatViewModel;
    headerLabel.textProperty().bindBidirectional(chatViewModel.getHeaderProperty());
    inputField.textProperty().bindBidirectional(chatViewModel.getInputProperty());
    errorLabel.textProperty().bindBidirectional(chatViewModel.getErrorProperty());

    //  Because you are using a fkin list you can not use bindings, sadge
    //chatList.textProperty().bindBidirectional(chatViewModel.getListProperty());
    chatViewModel.getListProperty().addListener((obs,oldV,newV)->chatList.setText(newV.toString()+"\n"+chatList.getText()));

    reset();
  }
  public void reset()
  {
    chatViewModel.reset();
  }

  public Region getRoot()
  {
    return root;
  }


  //triggered by pressing the Send button
  @FXML public void sendPressed()
  {
    chatViewModel.send();
  }


}
