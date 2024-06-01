import model.Model;
import model.ModelManager;
import view.SimpleConsoleView;

import java.util.Scanner;

public class UppercaseMainClient
{
  public static void main(String args[]) throws Exception {
    Model model = new ModelManager();
    
    // simple console view
    SimpleConsoleView view = new SimpleConsoleView(model);
    
    //  Testing
    Scanner input = new Scanner(System.in);

    while(true){
      System.out.println("Convert?: ");
      String request = input.nextLine();
      System.out.println("Client requesting> " + request);
      String reply = model.convert(request);
      System.out.println("Server responds> " + reply);
    }

  }
}