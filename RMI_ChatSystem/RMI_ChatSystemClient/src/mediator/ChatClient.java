package mediator;

import utility.observer.event.ObserverEvent;
import utility.observer.javaobserver.PropertyChangeSubject;
import utility.observer.listener.RemoteListener;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

//  Chat system <- model <- viewModel (for displaying after broadcast pops)
public class ChatClient implements RemoteListener<MessagePackage, MessagePackage>, PropertyChangeSubject  {
    private String host;
    private PropertyChangeSupport property;
    ServerInterface remoteModel;
    public ChatClient(String host) throws RemoteException {
        this.host = host;
        property = new PropertyChangeSupport(this);

        start();

        remoteModel.addListener(this);
    }
    private void start(){
        String nameForConnection = "ChatSystem";
        String serverLookUpAddress = "rmi://" + host + ":1099/" + nameForConnection;

        try {
            remoteModel = (ServerInterface) Naming.lookup(serverLookUpAddress);
            UnicastRemoteObject.exportObject(this,0);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            System.out.println("ServerInterface: " + e.getMessage());
        }


    }

    public void send(String username, String message) throws RemoteException {
        remoteModel.send(username,message);
    }
    public MessagePackage getWholeConversation() throws RemoteException {
        return remoteModel.getWholeConversation();
    }
    public void createUser(String username, String password) throws RemoteException{
        remoteModel.createUser(username,password);
    }

    @Override
    public void propertyChange(ObserverEvent<MessagePackage, MessagePackage> event) throws RemoteException {
        MessagePackage message = (MessagePackage) event.getValue2();
        property.firePropertyChange("broadcast", null, message);
    }


    @Override
    public void addListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName,listener);
    }

    @Override
    public void removeListener(String propertyName, PropertyChangeListener listener) {
        property.addPropertyChangeListener(propertyName,listener);
    }

    @Override
    public void addListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }

    @Override
    public void removeListener(PropertyChangeListener listener) {
        property.addPropertyChangeListener(listener);
    }
}
