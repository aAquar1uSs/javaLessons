package com.epam.rd.java.basic.practice6.part5;

public class Tree<E extends Comparable<E>> {

    private Node<E> root;
    private Node<E> parent;
    private Node<E> current;
    private boolean nodeIsNotFound = false;
    private static final int COUNT = 2;


    public boolean add(E element) {
        if (root == null) {
            root = new Node<>(element, null, null);
            return true;
        }
        return add(root, element);
    }

    public boolean add(Node<E> node, E element) {
        if (element.compareTo(node.getElement()) > 0) {
            if (node.rightBranch == null) {
                node.rightBranch = new Node<>(element, null, null);
                return true;
            }
            return add(node.rightBranch, element);
        }
        if (element.compareTo(node.element) < 0) {
            if (node.leftBranch == null) {
                node.leftBranch = new Node<>(element, null, null);
                return true;
            }
            return add(node.leftBranch, element);
        }
        return false;
    }

    public void add(E[] elements) {
        for (E element : elements) {
            add(element);
        }
    }

    public boolean remove(E element) {
        Node<E> heirNode;
        current = root;
        parent = root;
        boolean isRightNode = searchDirectionForNode(element);

        if (nodeIsNotFound) {
            return false;
        }

        removeIfTheNodeHasNoChild(isRightNode);
        replaceRightBranch(isRightNode);
        replaceLeftBranch(isRightNode);

        if(current.leftBranch != null && current.rightBranch != null) {
            heirNode = removeIfTwoChildBranchAreExist();
            if(current == root) {
                root = heirNode;
            }  else if(isRightNode) {
                parent.rightBranch = heirNode;
            } else {
                parent.leftBranch = heirNode;
            }
        }

        return true;
    }

    public boolean searchDirectionForNode(E element) {
        boolean isRightChild = false;

        while (current != null && current.getElement() != element) {
            parent = current;
            if (current.getElement().compareTo(element) > 0) {
                isRightChild = false;
                current = current.leftBranch;
            } else {
                isRightChild = true;
                current = current.rightBranch;
            }
        }
        if (current == null) {
            nodeIsNotFound = true;
        }
        return isRightChild;
    }

    public void removeIfTheNodeHasNoChild(boolean isRightNode) {
        if (current.leftBranch == null && current.rightBranch == null) {
            if (current == root) {
                root = null;
            } else if (isRightNode) {
                parent.rightBranch = null;
            } else {
                parent.leftBranch = null;
            }
        }
    }

    public void replaceRightBranch(boolean isRightNode) {
        if (current.rightBranch == null) {
            if (current == root) {
                root = current.leftBranch;
            } else if (isRightNode) {
                parent.rightBranch = current.leftBranch;
            } else {
                parent.leftBranch = current.leftBranch;
            }
        }
    }

    public void replaceLeftBranch(boolean isRightNode) {
        if (current.leftBranch == null) {
            if (current == root) {
                root = current.rightBranch;
            } else if (isRightNode) {
                parent.rightBranch = current.rightBranch;
            } else {
                parent.leftBranch = current.rightBranch;
            }
        }
    }

    public Node<E> removeIfTwoChildBranchAreExist() {
        Node<E> heirNode = current;
        Node<E> parentNode = current;
        Node<E> currentNode = current.rightBranch;
        while(currentNode != null) {
            parentNode = heirNode;
            heirNode = currentNode;
            currentNode = currentNode.leftBranch;
        }

        if(heirNode != current.rightBranch) {
            parentNode.leftBranch = heirNode.rightBranch;
            heirNode.rightBranch = current.rightBranch;
        }
        return heirNode;
    }

    public void printTree(Node<E> node,int space) {
        if (node == null) {
            return;
        }
        space += COUNT;
        printTree(node.leftBranch, space);

        for (int i = COUNT; i < space; i++)
            System.out.print(" ");
        System.out.print(node.getElement() + System.lineSeparator());

        printTree(node.rightBranch, space);

    }

    public void print() {
        printTree(root,0);
    }

    private static final class Node<E> {
        private final E element;
        private Node<E> rightBranch;
        private Node<E> leftBranch;

        public Node(E element, Node<E> right, Node<E> left) {
            this.element = element;
            this.rightBranch = right;
            this.leftBranch = left;
        }

        public E getElement() {
            return element;
        }

    }

}
