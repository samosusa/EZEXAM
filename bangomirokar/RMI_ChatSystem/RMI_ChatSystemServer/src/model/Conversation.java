package model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
  private String conversationContent;
  private List<String> lines;

  public Conversation() {
    lines = new ArrayList<>();
    conversationContent = "";
  }

  public void addPackage(String text) {
    lines.add(text);
    conversationContent += text + "\n";
  }

  public String getConversationContent() {
    return conversationContent;
  }
}
