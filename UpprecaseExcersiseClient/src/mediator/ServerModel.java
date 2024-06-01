package mediator;

import utility.UnnamedPropertyChangeSubject;
import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface ServerModel extends NamedPropertyChangeSubject {
    void connect();
    void disconnect();
    String convert(String s);
}
