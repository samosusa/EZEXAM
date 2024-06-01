import registry.RemoteMessageList;
import registry.RemoteSender;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiClient implements RemoteSender {

    private RemoteMessageList server;
    public RmiClient() throws RemoteException {
        try {
            //  getting the server interface from registry. "localhost" is placeholder of IP
            //  1099 stays usually the same, "Something" is the one you named!
            server = (RemoteMessageList) Naming.lookup("rmi://localhost:1099/Something");
        } catch (NotBoundException e) {
            throw new RuntimeException(e);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }

        //  Needed to connect the way back because, acts like peer to peer i guess
        UnicastRemoteObject.exportObject(this,0);
    }


    public void send(String text) throws RemoteException {
        server.addMessage(text, this);
    }

    @Override
    public void replyMessage(String message) throws RemoteException {
        System.out.println(message);
    }
}
