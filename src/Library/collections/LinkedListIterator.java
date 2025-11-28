package Library.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListIterator<T> implements Iterator<T>{

    private MyLinkedList.Node<T> actual;

    public LinkedListIterator(MyLinkedList.Node<T> start) {
        this.actual = start;
    }


    @Override
    public boolean hasNext() {
        return this.actual != null;
    }

    @Override
    public T next() {
        if(actual == null){
            throw new NoSuchElementException();
        }
        T dato = actual.data;
        actual = actual.next;
        return dato;
    }
    
}
