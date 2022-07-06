import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class MyLinkedList<Integer> implements List<Integer>, Deque<Integer> {

    transient int size = 0;
    transient Node<Integer> first;
    transient Node<Integer> last;
    transient Node<Integer> current;

    public MyLinkedList() {
    }

    private static class Node<Integer> {
        Integer item;
        Node<Integer> next;
        Node<Integer> prev;

        public Node(Node<Integer> prev, Integer integer, Node<Integer> next) {
            this.item = integer;
            this.next = next;
            this.prev = prev;
        }

        public boolean hasNext() {
            return this.next == null;
        }

        public boolean hasPrev() {
            return this.prev == null;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrev() {
            return this.prev;
        }

        public Integer getValue() {
            return this.item;
        }
    }

    /* I should add throw new NullPointerException(), but now it breaks the bottom statement
    * last = newNode */
    @Override
    public void addFirst(Integer integer) {
        Node<Integer> temp = first;
        Node<Integer> newNode = new Node<Integer>(null, integer, temp);
        first = newNode;
        if (temp == null) {
            last = newNode;
        } else temp.prev = newNode;
        size++;
    }

    @Override
    public void addLast(Integer integer) {
        Node<Integer> temp = last;
        Node<Integer> newNode = new Node<Integer>(last,integer,null);
        last = newNode;
        if (temp == null){
            first = newNode;
        } else temp.next = newNode;
        size++;
    }

    /* works as a addFirst method now, as I didn't add a NullPointerException() to that method
     */
    @Override
    public boolean offerFirst(Integer integer) {
        addFirst(integer);
        return true;
    }

    /* works as a addFirst method now, as I didn't add a NullPointerException() to that method
     */
    @Override
    public boolean offerLast(Integer integer) {
        addLast(integer);
        return true;
    }

    @Override
    public Integer removeFirst() {
        if(first == null){
            throw new NoSuchElementException();
        }
        else
            first = first.next;
            size--;
            return (Integer) first;
    }

    @Override
    public Integer removeLast() {
        if (last == null){
            throw new NoSuchElementException();
        }
        else
            last = last.prev;
            size--;
            return (Integer) last;
    }

    /*
    Deque has defined the method - Deque#pollFirst() and inherited the method - Queue#poll().
    So, LinkedList has basically these two methods defined for the two interfaces it implements.
    */
    @Override
    public Integer pollFirst() {
        return removeFirst();
    }

    @Override
    public Integer pollLast() {
        return removeLast();
    }

    @Override
    public Integer getFirst() {
        if (first == null){
            throw new NoSuchElementException();
        }
        else
            return first.item;
    }

    @Override
    public Integer getLast() {
        if (last == null){
            throw new NoSuchElementException();
        }
        else
            return last.item;
    }

    @Override
    public Integer peekFirst() {
        if (first == null){
            return null;
        }
        else
            return first.item;
    }

    @Override
    public Integer peekLast() {
        if (last == null){
            return null;
        }
        else
            return last.item;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        Node<Integer> current = first;
        if (first.item == o){
            first = first.next;
            size --;
            return true;
        }
        else
            for (int i = 1; i< size; i++){
                current = current.next;
                if (current.item == o) {
                    Node<Integer> temp = current.prev;
                    current = current.next;
                    current.prev = temp;
                    temp.next = current;
                    size --;
                    return true;
                }
        } return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        Node<Integer> current = last;
        if (last.item == o){
            last = last.prev;
            size --;
            return true;
        }
        else
            for (int i = 1; i< size; i++){
                current = current.prev;
                if (current.item == o) {
                    Node<Integer> temp = current.next;
                    current = current.prev;
                    current.next = temp;
                    temp.prev = current;
                    size --;
                    return true;
                }
            } return false;
    }

    @Override
    public boolean offer(Integer integer) {
        Node<Integer> temp = last;
        Node<Integer> newNode = new Node<Integer>(last,integer,null);
        last = newNode;
        if (temp == null){
            first = newNode;
        } else temp.next = newNode;
        size++;
        return true;
    }

    @Override
    public Integer remove() {
        return removeFirst();
    }

    @Override
    public Integer poll() {
        if(first == null){
            return null;
        }
        else
            first = first.next;
        size--;
        return (Integer) first;
    }

    @Override
    public Integer element() {
        if(first == null){
            throw new NoSuchElementException();
        }
        else return first.item;
    }

    @Override
    public Integer peek() {
        if(first == null){
            return null;
        }
        else return first.item;
    }

    @Override
    public void push(Integer integer) {
        addFirst(integer);
    }

    @Override
    public Integer pop() {
        return removeFirst();
    }


    //       DescendingIterator descendingIterator =  new DescendingIterator();
//        return descendingIterator.toString();
    @Override
    public Iterator<Integer> descendingIterator() {
        throw new NotImplementedException();
    }



//        private class DescendingIterator implements Iterator<Object>{
//        private Node<Integer> current;
//
//        public DescendingIterator() {
//            current = last;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return current.hasPrev();
//        }
//
//
//        @Override
//        public Integer next() {
//            if (!current.hasPrev()) {
//                throw new NoSuchElementException();
//            }
//
//            current = current.getPrev();
//            return current.getValue();
//        }
//
//        @Override
//        public void remove() {
//            throw new NotImplementedException();
//        }
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        if (size > 0){
            return false;
        }
        return true;
    }

    @Override
    public boolean contains(Object o) {
        Node<Integer> current = first;
        if (first.item == o){
            return true;
        }
        else
            for (int i = 1; i< size; i++){
                current = current.next;
                if (current.item == o) {
                    return true;
                }
            } return false;
    }

    @Override
    public Iterator<Integer> iterator() {
        throw new NotImplementedException();
    }

    @Override
    public Object[] toArray() {
        Object[] result = new Object[size];
        int i = 0;
        for (Node<Integer> current = first; current != null; current = current.next)
            result[i++] = current.item;
        return result;
    }

    @Override
    public <T> T[] toArray(T[] a) {
        throw new NotImplementedException();
    }

    @Override
    public boolean add(Integer integer) {
        Node<Integer> temp = first;
        Node<Integer> newNode = new Node<Integer>(null, integer, temp);
        first = newNode;
        if (temp == null) {
            last = newNode;
        } else temp.prev = newNode;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node<Integer> current = first;
        if (first.item == o){
            first = first.next;
            size --;
            return true;
        }
        else
            for (int i = 1; i< size; i++){
                current = current.next;
                if (current.item == o) {
                    Node<Integer> temp = current.prev;
                    current = current.next;
                    current.prev = temp;
                    temp.next = current;
                    size --;
                    return true;
                }
            } return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(Collection<? extends Integer> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean addAll(int index, Collection<? extends Integer> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public Integer get(int index) {
        Node<Integer> current = first;
        for (int i = 0; i<=size-1; i++){
            if (i == index){
                return current.item;
            }
            current = current.next;
        }
        return null;
    }

    @Override
    public Integer set(int index, Integer element) {
        Node<Integer> current = first;
        Integer temp;

        for (int i = 0; i<=size-1; i++){
            if (i == index){
                temp = current.item;
                current.item = element;
                return temp;
            }
            current = current.next;
        }
        return null;
    }

    //I have a bug here, so list cant to be printed.
    // method toString is not executed/ But work after other methods
    @Override
    public void add(int index, Integer integer) {
        throw new NotImplementedException();

        /*
        Node<Integer> current = first;
        ifStatement:
        if(index < size){
            for (int i = 0; i<=size; i++){
                if (i == index){
                    Node<Integer> newNode = new Node <>(current.prev,integer,current);
                    current.prev = newNode;
                    size++;
                    break ifStatement;
                    }
                current = current.next;
                }
            }
        if (index == size){
            addLast(integer);
        }
        if (index == 0) {
            addFirst(integer);
        }
         */
    }

    @Override
    public Integer remove(int index) {
        Node<Integer> current = first;
        if (index == 0){
            Integer temp = first.item;
            first = first.next;
            size --;
            return temp;
        }
        else
            for (int i = 1; i< size; i++){
                current = current.next;
                if (i == index) {
                    Integer temp = current.item;
                    Node<Integer> temper = current.prev;
                    current = current.next;
                    current.prev = temper;
                    temper.next = current;
                    size --;
                    return temp;
                }
            } return null;
    }

    @Override
    public int indexOf(Object o) {
        Node<Integer> current = first;
        if (first.item == o){
            return 0;
        }
        else
            for (int i = 1; i< size; i++){
                current = current.next;
                if (current.item == o) {
                    return i;
                };
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node<Integer> current = last;
        if (last.item == o){
            return size-1;
        }
        else
            for (int i = 1; i< size; i++){
                current = current.prev;
                if (current.item == o) {
                    return size-i-1;
                }
        }return -1;
    }

    @Override
    public ListIterator<Integer> listIterator() {
        return null;
    }

    @Override
    public ListIterator<Integer> listIterator(int index) {
        return null;
    }

    @Override
    public List<Integer> subList(int fromIndex, int toIndex) {
        return null;
    }


    @Override
    public String toString() {
        Node<Integer> temp = first;
        StringBuilder string = new StringBuilder(temp.item.toString() + " ");
        if (size == 0) {
            throw new NotImplementedException();}
        else
            for (int i = 0; i < (size-1); i++){
                temp = temp.next;
                string.append(temp.item + " ");
            }
            return string.toString();
    }
}
