package mediator;

import model.ChatModel;
import utility.observer.listener.GeneralListener;
import utility.observer.subject.PropertyChangeHandler;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class ChatServer implements ServerInterface{

    private ChatModel model;
    private PropertyChangeHandler<MessagePackage, MessagePackage> property;
    public ChatServer(ChatModel model){
        this.model = model;

        this.property = new PropertyChangeHandler<>(this,true);

        startRegistry();
        startServer();
    }

    private void startRegistry(){
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            System.out.println("ChatServer: " + e.getMessage());
        }
    }
    private void startServer(){
        try {
            UnicastRemoteObject.exportObject(this, 0);
            Naming.rebind("ChatSystem", this);
        } catch (RemoteException | MalformedURLException e) {
            System.out.println("ChatServer: " + e.getMessage());
        }
    }

    @Override
    public void send(String username, String message) throws RemoteException {
        model.send(username,message);
        //  Here im sending whole conversation as a string,
        //  but could be appending messages to already existing stuff
        property.firePropertyChange("broadcast", null, new MessagePackage(model.getWholeConversation()));
    }

    @Override
    public MessagePackage getWholeConversation() throws RemoteException {
        return new MessagePackage(model.getWholeConversation());
    }

    @Override
    public void createUser(String username, String password) throws RemoteException {
        model.createUser(username,password);
    }

    @Override
    public boolean addListener(GeneralListener<MessagePackage, MessagePackage> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }

    @Override
    public boolean removeListener(GeneralListener<MessagePackage, MessagePackage> listener, String... propertyNames) throws RemoteException {
        return property.addListener(listener, propertyNames);
    }
}
