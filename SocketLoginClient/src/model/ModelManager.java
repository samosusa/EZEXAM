package model;

import mediator.UserClient;

import java.io.IOException;

public class ModelManager implements Model{
    private UserClient userClient;
    public ModelManager() throws IOException {
        userClient = new UserClient("localhost",2910);
    }

    @Override
    public void login(String userName, String password) throws IllegalStateException, IllegalArgumentException {
        //  Some stuff
        userClient.login(userName,password);

    }
}
