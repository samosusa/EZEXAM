package mediator;

import model.Model;
import model.ModelManager;
import model.Password;
import model.UserName;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.Socket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class UserServer implements RemoteModel{
    private Model model;
    public UserServer() {
        model = new ModelManager();   //  Creating the model on start of the server
        try {
            startRegistry();
            startServer();
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }
    private void startRegistry() throws RemoteException
    {
        try{
            LocateRegistry.createRegistry(1099);    //  Creating the registry
            System.out.println("Registry started...");
        } catch (java.rmi.server.ExportException e){
            System.out.println("Registry already started? ; " + e.getMessage());
        }
    }

    //  There is another way to do this by, extending UnicastRemoteServer and calling the super constructor
    private void startServer() throws RemoteException, MalformedURLException {
        //  This starts the server?
        UnicastRemoteObject.exportObject(this, 0); //   this returns a stub which u can use in Naming.rebind instead of "this"
        //  Binds it to a name?
        Naming.rebind("Login",this);
    }

    @Override
    public void addUser(String username, String password) throws RemoteException {
        model.addUser(username,password);
        System.out.println("Server created: " + username + ", " + password);
    }

    @Override
    public String getUser(String username) throws RemoteException {
        return model.getUserByName(username).getPassword().getPassword();
    }
}
