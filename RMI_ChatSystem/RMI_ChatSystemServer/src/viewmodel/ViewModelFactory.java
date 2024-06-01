package viewmodel;

import model.ChatModel;

public class ViewModelFactory {


  private ChatViewModel chatViewModel;
  private ViewModelState viewState;

  public ViewModelFactory(ChatModel model){
    viewState = new ViewModelState();

    chatViewModel = new ChatViewModel(model, viewState);
  }


  public ChatViewModel getChatViewModel() {
    return chatViewModel;
  }
}
