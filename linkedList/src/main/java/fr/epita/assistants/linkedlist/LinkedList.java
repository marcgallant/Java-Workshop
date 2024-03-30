package fr.epita.assistants.linkedlist;

public class LinkedList<T extends Comparable<T>> {

    public class Node {
        public T data;

        public Node next;

        /**
         * Initializes the node
         **/
        public Node (T data) {
            this.data = data;
            this.next = null;
        }

    }

    private int length;

    public Node head;

    /**
     * Initializes the list
     **/
    public LinkedList() {
        this.length = 0;
        this.head = null;
    }

    /**
     * Inserts the specified element into the list.
     * The elements must be sorted in ascending order.
     * null elements should be at the end of the list.
     *
     * @param e Element to be inserted
     **/
    public void insert(T e) {
        this.length++;
        Node elt = new Node(e);
        Node prev = null;
        Node cur = this.head;

        while (cur != null && cur.data.compareTo(e) < 0) {
            prev = cur;
            cur = cur.next;
        }

        if (prev != null) {
            prev.next = elt;
            elt.next = cur;
        }
        else {
            elt.next = this.head;
            this.head = elt;
        }
    }

    /**
     * Returns the n-th element in the list.
     *
     * @param i Index
     * @return The element at the given index
     * @throws IndexOutOfBoundsException if there is no element at this
     *                                   index.
     **/
    public T get(int i) {
        if (i < 0 || i >= this.length) {
            throw new IndexOutOfBoundsException();
        }

        Node cur = this.head;

        while (i != 0) {
            i--;
            cur = cur.next;
        }

        return cur.data;
    }

    /**
     * Removes the first occurrence of the specified element in the list.
     *
     * @param e Element to remove
     * @return returns the element that has been removed or null
     **/
    public T remove(T e) {
        this.length--;
        Node cur = this.head;
        Node prev = null;

        while (cur != null && cur.data.compareTo(e) != 0) {
            prev = cur;
            cur = cur.next;
        }

        if (cur == null)
            return null;

        if (prev != null) {
            prev.next = cur.next;
        }
        else {
            this.head = cur.next;
        }

        return cur.data;
    }

    /**
     * Returns the size of the list.
     *
     * @return Number of elements in the list
     **/
    public int size() {
        return this.length;
    }

    /**
     * Removes all elements from the list.
     **/
    public void clear() {
        this.length = 0;
        this.head = null;
    }
}
