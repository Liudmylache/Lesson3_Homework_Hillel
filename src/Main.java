import java.sql.SQLOutput;
import java.util.LinkedList;

public class Main {
    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();

        list.add(5);
        list.add(88);
        list.addLast(3);
        list.add(9);
        list.add(7);
        list.add(88);
        System.out.println(list);
        list.addFirst(0);
        System.out.println(list);
        list.remove(1);
        System.out.println(list);
        System.out.println(list.indexOf(0));
        list.add(88);

        list.addLast(5);
        System.out.println(list);
        System.out.println(list.lastIndexOf(5));




        //System.out.println(list.getLast()); if first == null



    }
}