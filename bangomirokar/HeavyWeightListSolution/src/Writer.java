import static java.lang.Thread.sleep;

public class Writer implements Runnable
{
  private ReadWriteAccess lock;
  public Writer(ReadWriteAccess lock)
  {
    this.lock=lock;
  }
  @Override public void run()
  {
    for(int i=0; i<3; i++)
    {
      try
      {
        sleep((long)((Math.random()*5000)+5000));
      }
      catch(InterruptedException e)
      {
        //
      }
      ReadWriteList list=lock.acquireWrite();
      list.write(200);
      lock.releaseWrite();
    }

  }
}
