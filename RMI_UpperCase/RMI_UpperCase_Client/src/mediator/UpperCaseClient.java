package mediator;



import java.net.MalformedURLException;

import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class UpperCaseClient{

    UpperCaseServer server; //  server variable
    private String host;
    public UpperCaseClient(String host){
        this.host = host;
        start();
    }
    private void start() {
        String nameForConnection = "Uppercase";
        String serverLookUpAddress = "rmi://" + host + ":1099/" + nameForConnection;

        try {
            server = (UpperCaseServer) Naming.lookup(serverLookUpAddress);
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            throw new RuntimeException(e);
        }
    }


    //  Using server Interface here
    public String convert(String text) throws RemoteException {
        return server.convert(text);
    }
}
