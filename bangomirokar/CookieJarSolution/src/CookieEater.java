
public class CookieEater implements Runnable
{
   private CookieJar jar;

   public CookieEater(CookieJar jar)
   {
      this.jar = jar;
   }

   @Override
   public void run()
   {
      for (int i = 1; i <= 8; i++)
      {
         jar.eat();
         System.out.println(
               Thread.currentThread().getName() + " eating cookie #" + i);
         spendSomeTime("EAT");
      }
   }

   private void spendSomeTime(String what)
   {
      try
      {
         Thread.sleep(500);
      }
      catch (InterruptedException e)
      {
         // ...
      }
   }

}
