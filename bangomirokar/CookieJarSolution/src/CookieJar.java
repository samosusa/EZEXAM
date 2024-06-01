import utility.collection.ArrayQueue;
import utility.collection.QueueADT;


public class CookieJar
{
   private QueueADT<Cookie> cookies;
   private int cookieCountWhenToBake;
   private int numberOfCookiesInTheOven;
   private int cookiePlateSize;

   public CookieJar(int jarSize, int cookieCountWhenToBake,
         int cookiePlateSize)
   {
      System.out.println("jarSize"+ jarSize);
      this.cookies = new ArrayQueue<>(jarSize);
      for (int i=0; i<jarSize; i++)
      {
         cookies.enqueue(new Cookie("Chocolate"));
      }
      this.cookieCountWhenToBake = cookieCountWhenToBake;
      this.cookiePlateSize = cookiePlateSize;
      this.numberOfCookiesInTheOven = 0;
   }

   public synchronized void startBaking()
   {
      while (cookies.size() > cookieCountWhenToBake)
      {
         try
         {
            System.out.println("Waiting to bake: "
                  + Thread.currentThread().getName() + " count=" + cookies.size());
            wait();
         }
         catch (InterruptedException e)
         {
            //
         }
      }
      System.out.println("Started baking: " + Thread.currentThread().getName()
            + " count=" + cookies.size());
      this.numberOfCookiesInTheOven = cookiePlateSize;
   }

   public synchronized int finishedBaking()
   {
      if (numberOfCookiesInTheOven <= 0)
      {
         System.out.println("No cookies in the oven: "
               + Thread.currentThread().getName() + " count=" + cookies.size());
         return 0;
      }
      this.numberOfCookiesInTheOven = 0;
      for (int i=0; i<cookiePlateSize; i++)
      {
         cookies.enqueue(new Cookie("Chocolate"));
      }
      System.out.println("Finished baking: " + Thread.currentThread().getName()
            + " count=" + cookies.size());
      notifyAll();
      return cookiePlateSize;
   }

   public synchronized void eat()
   {
      while (cookies.isEmpty())
      {
         try
         {
            System.out.println("Waiting to eat: "
                  + Thread.currentThread().getName() + " count=" + cookies);
            wait();
         }
         catch (InterruptedException e)
         {
            //
         }
      }
      Cookie cookie = cookies.dequeue();
      notify();
      System.out.println("Eating " + cookie + ": " + Thread.currentThread().getName()
            + " count=" + cookies.size());
   }

}
