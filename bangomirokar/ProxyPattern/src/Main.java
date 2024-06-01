public class Main {
    public static void main(String[] args) {
        ThreadSafeLinkedList<String> list = new ThreadSafeLinkedList<>();

        list.add("Alpha");
        list.add("Bravo");
        list.add(2,"Cecil");
        list.add("Delta");
        list.add("Foxtrot");

        System.out.println(list.get(4));

        System.out.println(list.remove(1));

        System.out.println(list.isEmpty());

        list.add("Garry");

        System.out.println(list.get(4));
    }
}