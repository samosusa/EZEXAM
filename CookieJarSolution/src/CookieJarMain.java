
public class CookieJarMain
{

   public static void main(String[] args)
   {
      CookieJar jar = new CookieJar(30, 5, 16);

      CookieEater[] eaters = new CookieEater[10];
      for (int i=0; i<eaters.length; i++)
      {
         eaters[i] = new CookieEater(jar);    
         new Thread(eaters[i], "Eater #" + (i+1)).start();
      }
      CookieBaker baker = new CookieBaker(jar);
      new Thread(baker, "Baker").start();
   }


}
