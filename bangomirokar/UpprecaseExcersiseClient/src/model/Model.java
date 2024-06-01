package model;



import utility.observer.javaobserver.NamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface Model extends NamedPropertyChangeSubject
{
  String convert(String source) throws Exception;
}
