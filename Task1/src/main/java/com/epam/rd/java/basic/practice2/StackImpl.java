package com.epam.rd.java.basic.practice2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackImpl implements Stack {

    private static class Node {
        private Object data;
        private Node nextLink;


        private Node(Object element) {
            this.data = element;
        }
    }

    private Node top;
    private int size = 0;

    @Override
    public void clear() {
        Node current = top;
        while (current.nextLink != null) {
            current.data = null;
            current = current.nextLink;
        }
        top = null;
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

        private Node current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                Object data = current.data;
                current = current.nextLink;
                return data;
            }
            throw new NoSuchElementException();
        }
    }

    @Override
    public void push(Object element) {
        Node temp = new Node(element);
        temp.nextLink = top;
        top = temp;
        size++;
    }

    @Override
    public Object pop() {
        Node temp;
        if (top == null) {
            return  null;
        }
        Object tmp = top.data;
        temp = top;
        top = top.nextLink;
        temp.nextLink = null;
        size--;
        return tmp;
    }

    @Override
    public Object top() {
        if(top == null) {
            return  null;
        }
        return top.data;
    }

    @Override
    public String toString() {
        if (top == null) {
            return "[" +
                    "]";
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (Node x = top; x != null; x = x.nextLink) {
            if(x.nextLink == null) {
                stringBuilder.insert(0,x.data);
            } else {
                stringBuilder.insert(0,x.data)
                        .insert(0,' ')
                        .insert(0,',');
            }
        }

        String tmp = stringBuilder.toString();
        tmp = tmp.trim();
        return "[" +
                tmp +
                "]";
    }

    public static void main(String[] args) {

        final String INFO_POP = "Pop = ";

        StackImpl stack = new StackImpl();

        stack.push("A");
        stack.push("B");
        stack.push("E");
        stack.push("G");
        stack.push("U");
        stack.push("I");
        stack.push(null);
        stack.push("O");
        stack.push("P");
        stack.push("C");


        System.out.println(stack.size());

        Iterator<Object> it = stack.iterator();

        while (it.hasNext()) {
            System.out.println(it.next());
        }

        System.out.println(INFO_POP + stack.pop());
        System.out.println(INFO_POP + stack.pop());
        System.out.println(INFO_POP + stack.pop());
        System.out.println("Top = " + stack.top());

        String tmp = stack.toString();
        System.out.println(tmp);
    }

}
