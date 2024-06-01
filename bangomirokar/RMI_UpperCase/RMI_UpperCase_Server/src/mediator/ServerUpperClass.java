package mediator;

import model.Model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServerUpperClass implements UpperCaseServer{

    private Model model;

    public ServerUpperClass(Model model){
        this.model = model;

        //  Starting the server and creating registry in constructor
        try {
            startRegistry();
            startServer();
        } catch (RemoteException | MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    private void startRegistry() throws RemoteException {
        try{
            LocateRegistry.createRegistry(1099);
        } catch (java.rmi.server.ExportException e){
            System.out.println("Registry already at this port ; " + e.getMessage());
        }
    }
    private void startServer() throws RemoteException, MalformedURLException {
        UnicastRemoteObject.exportObject(this,0);
        Naming.rebind("Uppercase",this);
    }

    @Override
    public String convert(String text) throws RemoteException {
        try {
            return model.convert(text);
        } catch (Exception e) {
            System.out.println("Model: " + e.getMessage());
            return null;
        }
    }
}
