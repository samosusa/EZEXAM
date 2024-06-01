package mediator;

import java.io.Serializable;

public class MessagePackage implements Serializable {
    private String message;

    public MessagePackage(String s) {
        setMessage(s);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String s) {
        message = s;
    }

    @Override
    public String toString() {
        return message;
    }
}
