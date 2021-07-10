public class LinkedListDeque<T> implements Deque<T> {
    private static class Node<T> {
        private T item;
        private Node prev;
        private Node next;

        private Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private static int size;

    public LinkedListDeque() {
        sentinel = new Node(-1, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node p = new Node(item, sentinel, sentinel.next);
        //set the one pointing to sentinel point to new node
        sentinel.next.prev = p;
        //set new node as current node
        sentinel.next = p;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node p = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node p = sentinel.next;
        while (p.next != sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    @Override
    public T removeFirst() {
        T result = null;
        if (!isEmpty()) {
            result = (T) sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
        }
        return result;
    }

    @Override
    public T removeLast() {
        T result = null;
        if (!isEmpty()) {
            result = (T) sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
        }
        return result;
    }

    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        while (index > 0) {
            p = p.next;
            index -= 1;
        }
        return (T) p.item;
    }


    private Node getRecursive(int index, Node p) {
        if (index == 0) {
            return p;
        }
        p = p.next;
        index -= 1;
        return getRecursive(index, p);
    }
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        Node p = sentinel.next;
        p = getRecursive(index, p);
        return (T) p.item;
    }

}
