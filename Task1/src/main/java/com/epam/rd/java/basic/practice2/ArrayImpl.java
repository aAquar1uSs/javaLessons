package com.epam.rd.java.basic.practice2;


import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayImpl implements Array {

    private Object[] arrayValues;
    private int size;
    private int maxSize;


    public ArrayImpl(int initCapacity) {
        this.maxSize = initCapacity;
        if (maxSize > 0) {
            this.arrayValues = new Object[maxSize];
        } else
            throw new NoSuchElementException();
    }


    @Override
    public void clear() {
        for (int i = 0; i < size; i++)
            arrayValues[i] = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private class IteratorImpl implements Iterator<Object> {

        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public Object next() {
            if (hasNext()) {
                return arrayValues[currentIndex++];
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove() {
            if (currentIndex < size) {
                int numMoved = size - currentIndex - 1;
                Object[] temp = new Object[size];
                System.arraycopy(arrayValues, currentIndex + 1, temp, 0, numMoved);
                size--;
                arrayValues = temp;
                currentIndex = 0;
            }
        }

    }

    @Override
    public void add(Object element) {
        if (size < maxSize) {
            arrayValues[size++] = element;
        } else {
            Object[] temp = new Object[arrayValues.length + 1];
            System.arraycopy(arrayValues, 0, temp, 0, arrayValues.length);
            temp[size++] = element;
            arrayValues = temp;
        }
    }

    @Override
    public void set(int index, Object element) {
        if (index >= size || index < 0) {
            throw new NoSuchElementException();
        }
        arrayValues[index] = element;
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        return arrayValues[index];
    }

    @Override
    public int indexOf(Object element) {
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (arrayValues[i] == null)
                    return i;
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (element.equals(arrayValues[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new NoSuchElementException();
        }
        int numMoved = size - index - 1;
        System.arraycopy(arrayValues, index + 1, arrayValues, index, numMoved);
        arrayValues[--size] = null;
    }

    @Override
    public String toString() {
        StringBuilder strBuilder = new StringBuilder();
        String tmp;
        for (int i = 0; i < size; i++) {
            strBuilder.append(arrayValues[i])
                    .append(',')
                    .append(' ');
        }
        tmp = strBuilder.toString();
        tmp = tmp.trim();
        return "[" +
                tmp.substring(0, tmp.length() - 1) +
                "]";
    }

    public static void main(String[] args) {

        ArrayImpl arr = new ArrayImpl(10);
        arr.add('R');
        arr.add('O');
        arr.add('K');
        arr.add('L');
        arr.add('V');
        arr.add('N');


        Iterator<Object> it = arr.iterator();

        while (it.hasNext()) {
            System.out.println("Елементы массива = " + it.next());
        }

        System.out.println("Размер массива = " + arr.size());

        System.out.println("Получаем елемент массива под индексом '0' = " + arr.get(0));
        System.out.println("Добавляем в массив елемент 'R'");
        arr.add('R');
        System.out.println("Выводим размер массива =" + arr.size());
        System.out.println("Удаляем елемент массива под индексом '3'");
        arr.remove(3);
        String tmp = arr.toString();
        System.out.println(tmp);


    }

}