public class Main {
    public static void main(String[] args) {

        //  Implemented with writers preference

        HeavyWeightList list = new HeavyWeightList(3,5);
        ReadWriteAccess sharedResource = new ListAccess(list);

        Thread[] writers = new Thread[2];
        for (int i = 0; i < writers.length; i++) {
            Writer writer = new Writer(sharedResource);
            writers[i] = new Thread(writer, "Writer"+i);
            writers[i].start();
        }
        Thread[] readers = new Thread[25];
        for (int i = 0; i < readers.length; i++) {
            Reader reader = new Reader(sharedResource);
            readers[i] = new Thread(reader, "Reader"+i);
            readers[i].start();
        }

    }
}