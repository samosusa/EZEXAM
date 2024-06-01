package model;

import mediator.UserClient;

import java.io.IOException;
import java.rmi.RemoteException;

public class ModelManager implements Model{

    UserClient server;
    public ModelManager(UserClient server) throws IOException {
        this.server = server;
    }

    @Override
    public void login(String userName, String password) throws IllegalStateException, IllegalArgumentException, RemoteException {
        server.addUser(userName,password);
    }

    @Override
    public String getUser(String username) throws RemoteException {
        return server.getUser(username);
    }
}
