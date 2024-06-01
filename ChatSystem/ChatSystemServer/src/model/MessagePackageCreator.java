package model;

public class MessagePackageCreator extends PackageCreator {

  @Override
  protected Package createPackage(String sender, String request, Object reply)
  {
    //  This was way simplified just for it to work....
    return new MessagePackage(sender, request, null);
  }
}
