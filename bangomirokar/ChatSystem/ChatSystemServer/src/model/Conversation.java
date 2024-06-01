package model;

import java.util.ArrayList;
import java.util.List;

public class Conversation {
  private String conversationContent;
  private List<Package> packages;

  public Conversation() {
    packages = new ArrayList<>();
    conversationContent = "";
  }

  public void addPackage(Package pack) {
    packages.add(pack);
    System.out.println("added");
    conversationContent += pack.toString() + "\n";
  }

  public String getConversationContent() {
    return conversationContent;
  }
}
