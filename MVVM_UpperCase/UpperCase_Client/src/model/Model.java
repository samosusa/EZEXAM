package model;



import utility.observer.javaobserver.NamedPropertyChangeSubject;

public interface Model extends NamedPropertyChangeSubject
{
  String convert(String source) throws Exception;
}
