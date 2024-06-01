package mediator;

import utility.observer.subject.RemoteSubject;


import java.rmi.RemoteException;

//  Tried to use "String" instead of "MessagePackage" for Observer ... it does not work either way :c
public interface ServerInterface extends RemoteSubject<MessagePackage, MessagePackage> {
    void send(String username, String message) throws RemoteException;
    MessagePackage getWholeConversation()  throws RemoteException;
    void createUser(String username, String password) throws RemoteException;
}
