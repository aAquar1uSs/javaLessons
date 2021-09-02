package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ListImpl implements List {

    private static class Node {

        private Object data;
        private Node nextElement;

        private Node(Object data) {
            this.data = data;
        }

        public void setNextElement(Node nextElement) {
            this.nextElement = nextElement;
        }

        public Object getData() {
            return data;
        }
    }

    private Node head;
    private int size;


    @Override
    public void clear() {
        Node current = head;
        while (current.nextElement != null) {
            current.data = null;
            current = current.nextElement;
        }
        head = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        Node current = head;
        Node prev = null;

        private IteratorImpl() {
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            prev = current;
            Object data = current.getData();
            current = current.nextElement;
            return data;
        }
    }

    @Override
    public void addFirst(Object element) {
        Node newNode = new Node(element);
        newNode.setNextElement(head);
        head = newNode;
        size++;
    }

    @Override
    public void addLast(Object element) {
        if (head == null) {
            head = new Node(element);
        } else {
            Node current = head;
            while (current.nextElement != null) {
                current = current.nextElement;
            }
            current.setNextElement(new Node(element));
        }
        size++;
    }

    @Override
    public void removeFirst() {
        Node nextNode = head.nextElement;
        head = null;
        head = nextNode;
        size--;
    }

    @Override
    public void removeLast() {
        Node current = head;
        Node prev = current;
        while (current.nextElement != null) {
            prev = current;
            current = current.nextElement;
        }
        prev.setNextElement(null);
        size--;

    }

    @Override
    public Object getFirst() {
        if (head == null) {
            return null;
        }
        return head.getData();
    }

    @Override
    public Object getLast() {
        if (head == null) {
            return null;
        }
        Node current = head;
        while (current.nextElement != null) {
            current = current.nextElement;
        }
        return current.getData();
    }

    @Override
    public Object search(Object element) {
        StringBuilder strBuild = new StringBuilder();
        if (head == null) {
            return null;
        }

        if (head.getData() == element) {
            strBuild.append(head.getData());
            return strBuild.toString();
        }

        Node current = head;
        while (current.nextElement != null) {
            if (current.nextElement.getData() == element) {
                strBuild.append(current.nextElement.getData());
                return strBuild.toString();
            }
            current = current.nextElement;
        }

        return null;
    }

    @Override
    public boolean remove(Object element) {
        if (head == null)
            return false;

        if (head.getData() == element) {
            head = head.nextElement;
            size--;
            return true;
        }

        Node current = head;
        while (current.nextElement != null) {
            if (current.nextElement.getData() == element) {
                current.setNextElement(current.nextElement.nextElement);
                size--;
                return true;
            }
            current = current.nextElement;
        }
        return false;
    }

    @Override
    public String toString() {
        if (head == null) {
            return "[" +
                    "]";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (Node x = head; x != null; x = x.nextElement) {
            if (x.nextElement == null) {
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

        ListImpl lst2 = new ListImpl();

        lst2.addFirst("G");
        lst2.addLast("A");
        lst2.addLast("B");
        lst2.addLast("C");
        lst2.addLast("D");

        Iterator<Object> it = lst2.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println("Размер списка  = " + lst2.size());

        System.out.println("Удаляем из списка елемент 'D'");
        lst2.remove("D");

        System.out.println("Елемент найден = " + lst2.search("C"));
        System.out.println("Последний елемент списка = " + lst2.getLast());
        System.out.println("Первый елемент списка = " + lst2.getFirst());
        lst2.removeLast();
        System.out.println("Удаляем последний елемент!");

        lst2.clear();

        String temp = lst2.toString();
        System.out.print(temp);


    }
}
