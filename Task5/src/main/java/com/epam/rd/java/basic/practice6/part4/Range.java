package com.epam.rd.java.basic.practice6.part4;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Range implements Iterable<Integer> {
    private final int start;
    private final int end;
    private final boolean reverse;

    public Range(int n, int m) {
        this(n, m, false);
    }

    public Range(int firstBound, int secBound, boolean reversedOrder) {
        this.reverse = reversedOrder;
        if (!reverse) {
            if (firstBound < secBound) {
                this.start = firstBound;
                this.end = secBound;
            } else {
                throw new IllegalArgumentException();
            }
        } else {
            if (firstBound < secBound) {
                this.start = secBound;
                this.end = firstBound;
            } else {
                throw new IllegalArgumentException();
            }
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return new IteratorImpl(start, end, reverse);
    }

    private final class IteratorImpl implements Iterator<Integer> {

        private int cursor;
        private final int end;
        private final boolean reverse;

        public IteratorImpl(int start, int end, boolean reverse) {
            this.cursor = start;
            this.end = end;
            this.reverse = reverse;
        }

        @Override
        public boolean hasNext() {
            if (reverse) {
                return cursor >= end;
            }
            return cursor <= end;
        }

        @Override
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            int element = cursor;
            if (reverse) {
                cursor--;
            } else {
                cursor++;
            }
            return element;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

}
