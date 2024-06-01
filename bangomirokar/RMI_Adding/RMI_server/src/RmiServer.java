import registry.RemoteMessageList;
import registry.RemoteSender;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RmiServer implements RemoteMessageList {
    private ArrayList<String> messages;
    private ArrayList<RemoteSender> senders;
    public RmiServer(){
        messages = new ArrayList<>();
        senders = new ArrayList<>();

    }

    public void start() throws RemoteException, MalformedURLException {
        //  This starts the server?
        UnicastRemoteObject.exportObject(this, 0);
        //  Binds it to a name?
        Naming.rebind("Something",this);
    }
    @Override
    public void addMessage(String message, RemoteSender sender) throws RemoteException {
        messages.add(message);

        //  Just adding to senders for now
        if(!senders.contains(sender)){
            senders.add(sender);
        }

        sender.replyMessage("You've sent a " + message);
    }
}
