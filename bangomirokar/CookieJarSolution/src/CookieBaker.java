
public class CookieBaker implements Runnable
{
   private CookieJar jar;

   public CookieBaker(CookieJar jar)
   {
      this.jar = jar;
   }

   @Override
   public void run()
   {
      while (true)
      {
         jar.startBaking();
         spendSomeTime("BAKING");
         int cookies = jar.finishedBaking();
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
