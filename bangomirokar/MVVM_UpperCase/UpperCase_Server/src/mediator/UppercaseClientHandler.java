package mediator;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.net.*;

import com.google.gson.Gson;
import javafx.application.Platform;
import model.Model;

public class UppercaseClientHandler implements Runnable, PropertyChangeListener
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Model model;
  private Gson gson;
  private boolean running;

  public UppercaseClientHandler(Socket socket, Model model)
      throws IOException
  {
    this.model = model;
    this.socket = socket;

    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    gson = new Gson();

    model.addListener(this);
  }

  @Override
  public void run()
  {
    running = true;
    while (running)
    {
      try
      {


        String json = in.readLine();
        NetworkPackage pack = gson.fromJson(json,NetworkPackage.class);

        String request = pack.getContent();
        model.addLog("Client> " + request);
        String reply = model.convert(request);
        model.addLog("Server> " + reply);
        NetworkPackage replyPack = new NetworkPackage("reply", reply);
        String jsonPack = gson.toJson(replyPack);
        out.println(jsonPack);
        if (request.contentEquals("quit"))
        {
          close();
        }
      }
      catch (Exception e)
      {
        model.addLog("Client error");
        close();
      }
    }
    close();
  }
  
  public void close()
  {
    running = false;
    try
    {
      in.close();
      out.close();
      socket.close();
    }
    catch (IOException e)
    {
      //
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent evt) {
    Platform.runLater(() -> {
      if("broadcast".equals(evt.getPropertyName())){
        NetworkPackage pack = new NetworkPackage("Message", (String) evt.getNewValue());
        String jsonToSend = gson.toJson(pack);
        out.println(jsonToSend);
      }
    });
  }
}
