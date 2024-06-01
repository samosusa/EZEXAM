package model;

import utility.observer.javaobserver.NamedPropertyChangeSubject;


public interface ChatModel extends NamedPropertyChangeSubject
{
    void send(String username, String message); //used for sending the message, on Send button
    String getWholeConversation(); //used to reload the updated conversation, after send() was called
    void createUser(String username, String password) throws IllegalArgumentException; // for user creation

}
