import java.util.ArrayDeque;
import java.util.Queue;

public class ListAccess implements ReadWriteAccess
{
  private int readers, writers;
  private Queue<Thread> queue;
  private HeavyWeightList list;
  public ListAccess(HeavyWeightList list)
  {
    this.list=list;
    readers=0;
    writers=0;
    queue=new ArrayDeque<>();
  }
  //  Fair solution
  @Override public synchronized ReadList acquireRead()
  {
    queue.offer(Thread.currentThread());
    System.out.println(Thread.currentThread().getName() + " is added to the queue. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());
    while(queue.peek()!=Thread.currentThread())
    {
      try
      {
        System.out.println(Thread.currentThread().getName() + " is waiting in the queue. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

        wait();
      }
      catch (InterruptedException e)
      {
        //
      }
    }
    System.out.println(Thread.currentThread().getName() + " is the first one in the queue! Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

    while(writers>0)
      {
        try
        {
          System.out.println(Thread.currentThread().getName() + " is waiting for the writer. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

          wait();
        }
        catch(InterruptedException e)
        {
          //
        }
      }
    readers++;
      queue.remove();
    System.out.println(Thread.currentThread().getName() + " is reading. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

    notifyAll();
      return list;
  }

  @Override public synchronized void releaseRead()
  {
    readers--;
    System.out.println(Thread.currentThread().getName() + " is releasing the read. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

    if(readers==0)
      notifyAll();
  }

  @Override public synchronized ReadWriteList acquireWrite()
  {
    queue.offer(Thread.currentThread());
    System.out.println(Thread.currentThread().getName() + " is added to the queue. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

    while(queue.peek()!=Thread.currentThread())
    {
      try
      {
        System.out.println(Thread.currentThread().getName() + " is waiting in the queue. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

        wait();
      }
      catch (InterruptedException e)
      {
        //
      }
    }
    System.out.println(Thread.currentThread().getName() + " is the first one in the queue. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

    while(writers>0 || readers>0)
    {
      try
      {
        System.out.println(Thread.currentThread().getName() + " is waiting for everyone to leave. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

        wait();
      }
      catch(InterruptedException e)
      {
        //
      }
    }

    writers++;
    queue.remove();
    System.out.println(Thread.currentThread().getName() + " is writing. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

    return list;
  }

  @Override public synchronized void releaseWrite()
  {
    writers--;
    System.out.println(Thread.currentThread().getName() + " is releasing the write. Writers: " + writers + "; Readers: " + readers+ "; In the queue :"+ queue.size());

    notifyAll();
  }
}
