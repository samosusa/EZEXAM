import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class RmiTaskServer implements RemoteTaskList{

    private TaskList tasks;
    public RmiTaskServer(){
        tasks = new TaskList();
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
            Registry reg = LocateRegistry.createRegistry(1099);
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
        Naming.rebind("Case",this);
    }

    @Override
    public void add(Task task) throws RemoteException {
        tasks.add(task);
    }

    @Override
    public Task get() throws RemoteException {
        return tasks.getAndRemoveNextTask();
    }

    @Override
    public int size() throws RemoteException {
        return tasks.size();
    }
}
