package demos.queue;


import dataStructures.queue.EmptyQueueException;
import dataStructures.queue.LinkedQueue;
import dataStructures.queue.Queue;

public class LinkedPriorityQueue<T extends Comparable<? super T>>
        implements PriorityQueue<T> {
    private static class Node<E> {
        private E elem;
        private Node<E> next;

        public Node(E x, Node<E> node) {
            elem = x;
            next = node;
        }
    }
    //private LinkedQueue<T> q;
    private Node<T> first, last;
    public LinkedPriorityQueue(){
        first = null;
        last = null;
    }
    @Override
    public void enqueue(T elem) {
        Node<T> node = new Node<>(elem, null);

        if (first == null || elem.compareTo(first.elem) < 0) {
            // Si la cola está vacía o el nuevo elemento tiene mayor prioridad que el primer elemento
            node.next = first;
            first = node;

            if (node.next == null) {
                // Si la cola estaba vacía, el nuevo nodo es también el último
                last = node;
            }
        } else {
            // Insertar en la posición correcta manteniendo la prioridad
            Node<T> current = first;

            while (current.next != null && elem.compareTo(current.next.elem) >= 0) {
                current = current.next;
            }

            node.next = current.next;
            current.next = node;

            if (node.next == null) {
                // Si el nuevo nodo es el último de la cola, actualizar 'last'
                last = node;
            }
        }
    }

    @Override
    public void dequeue() {
        if (isEmpty()) {
            throw new EmptyQueueException("Cola vacía. No se puede realizar dequeue.");
        }

        first = first.next;

        if (first == null) {
            // Si la cola se ha vuelto vacía, actualizar 'last'
            last = null;
        }
    }

    @Override
    public T first() {
        return first.elem;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }
    public String toString() {
        String className = getClass().getSimpleName();
        String s = className + "(";
        for (Node<T> node = first; node != null; node = node.next){
            s += node.elem + (node.next != null ? "," : "");
        }
        s += ")";
        System.out.println(s);
        return s;
    }
    public static void main(String[] args) {
        LinkedPriorityQueue<Integer> q = new LinkedPriorityQueue<>();
        //System.out.println("jofsdaf");
        q.enqueue(2);
        q.toString();
        q.enqueue(3);
        q.toString();
        q.enqueue(0);
        q.enqueue(5);
        q.toString();
    }
}
