public class Test
{
  public static void main(String[] args)
  {

    //  Implemented with fair solution

    HeavyWeightList list=new HeavyWeightList(3, 5);
    ReadWriteAccess access=new ListAccess(list);
    for(int i=0; i<25; i++)
    {
      Reader reader=new Reader(access);
      new Thread(reader, "Reader "+ i).start();
    }
    for(int i=0; i<2; i++)
    {
      Writer writer=new Writer(access);
      new Thread(writer, "Writer "+ i).start();
    }

  }
}
