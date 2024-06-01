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
    chatList.textProperty().bindBidirectional(chatViewModel.getListProperty());
    errorLabel.textProperty().bindBidirectional(chatViewModel.getErrorProperty());

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





}
