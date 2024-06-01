import static java.lang.Thread.sleep;

public class Reader implements Runnable
{
  private ReadWriteAccess lock;
  public Reader(ReadWriteAccess lock)
  {
    this.lock=lock;
  }
  @Override public void run()
  {
    for(int i=0; i<3; i++)
    {
      try
      {
        sleep((long)((Math.random()*9000)+1000));
      }
      catch(InterruptedException e)
      {
        //
      }
      ReadList list=lock.acquireRead();
      list.read();
      lock.releaseRead();
    }
  }
}
