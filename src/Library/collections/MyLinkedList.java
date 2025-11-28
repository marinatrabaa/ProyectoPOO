package Library.collections;

import java.lang.classfile.components.ClassPrinter.Node;
import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {

    private Node head;
    private Node tail;
    private int size = 0;

    /**
     * Nested class which represents a node from the list.
     */
    protected static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
        }
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public boolean contains(T data){
        for(T d: this){
            if(d.equals(data)){
                return true;
            }
        }
        return false;
    }

    public T get(int index){
        if(index < 0 || index > this.size){
            throw new IndexOutOfBoundsException();
        }

        Node<T> actual = head;
        for (int i = 0; i < index; i++) {
            actual = actual.next;
        }
        return actual.data;
    }

    public void add(T data){
        this.add(data);
    }

    public boolean remove(T data){
        return this.remove(data);
    }


    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator(head);
    }
    
}
