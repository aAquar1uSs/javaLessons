package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class QueueImpl implements Queue {


    private static class Node {

        private Object data;
        private Node next;

        private Node(Object data) {
            this.data = data;
        }

        public Object getData() {
            return data;
        }

    }

    private Node head = null;
    private Node tail = null;
    private int size;


    @Override
    public void clear() {
        Node curr = head;
        while (curr.next != null) {
            curr.data = null;
            curr = curr.next;
        }
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }


    private class IteratorImpl implements Iterator<Object> {

        Node current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Object data = current.getData();
            current = current.next;
            return data;
        }
    }

    @Override
    public void enqueue(Object element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
        size++;
    }

    @Override
    public Object dequeue() {
        if (isEmpty()) {
            return null;
        }
        Object element = head.getData();
        head = head.next;
        size--;
        return element;
    }

    @Override
    public Object top() {
        return head.getData();
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[" +
                    "]";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (Node x = head; x != null; x = x.next) {
            if(x.next == null) {
                stringBuilder.append(x.getData());
            } else {
                stringBuilder.append(x.getData())
                        .append(',')
                        .append(' ');
            }
        }

        String tmp = stringBuilder.toString();
        tmp = tmp.trim();
        return "[" +
                tmp +
                "]";
    }

    public static void main(String[] args) {

        final String INFO = "Елемент который удалили = ";
        final String INFO_SIZE_QUEUE = "Размер очереди = ";

        QueueImpl queue = new QueueImpl();

        queue.enqueue("A");
        queue.enqueue("B");
        queue.enqueue("C");
        queue.enqueue("D");
        queue.enqueue("E");

        Iterator<Object> it = queue.iterator();
        System.out.println("--------------------------------");
        while (it.hasNext()) {
            System.out.println(it.next());
        }


        System.out.println("--------------------------------");
        System.out.println("Top element = " + queue.top());
        System.out.println(INFO_SIZE_QUEUE + queue.size());

        System.out.println(INFO + queue.dequeue());
        System.out.println(INFO + queue.dequeue());
        System.out.println(INFO + queue.dequeue());


        System.out.println("Top element = " + queue.top());
        System.out.println(INFO_SIZE_QUEUE + queue.size());

        String temp = queue.toString();
        System.out.println(temp);

        System.out.println("Очистка очереди");
        queue.clear();

        temp = queue.toString();
        System.out.print(temp);
    }

}
