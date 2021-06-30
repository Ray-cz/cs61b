public class LinkedListDeque<T> {
    public static class Node<T> {
        private T item;
        private Node prev;
        private Node next;

        public Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel;
    private static int size;

    public LinkedListDeque(int x) {
        sentinel = new Node(-1, sentinel, sentinel);
        sentinel.next = sentinel.prev = new Node(x, sentinel, sentinel);
        size = 1;
    }

    public LinkedListDeque() {
        sentinel = new Node(-1, sentinel, sentinel);
        size = 0;
    }


    public void addFirst(T item) {
        Node p = new Node(item, sentinel, sentinel.next);
        //set the one pointing to sentinel point to new node
        sentinel.next.prev = p;
        //set new node as current node
        sentinel.next = p;
        size += 1;
    }

    public void addLast(T item) {
        Node p = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = p;
        sentinel.prev = p;
        size += 1;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node p = sentinel.next;
        while(p.next != sentinel) {
            System.out.print(p.item);
            System.out.print(" ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    public T removeFirst() {
        T result = null;
        if (!isEmpty()) {
            T first_item = (T) sentinel.next.item;
            sentinel.next = sentinel.next.next;
            sentinel.next.prev = sentinel;
            size -= 1;
            result = first_item;
        }
        return result;
    }

    public T removeLast() {
        T result = null;
        if (!isEmpty()) {
            T last_item = (T) sentinel.prev.item;
            sentinel.prev = sentinel.prev.prev;
            sentinel.prev.next = sentinel;
            size -= 1;
            result = last_item;
        }
        return result;
    }

    public T get(int index){
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


    public Node getRecursive(int index, Node p) {
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



    /**
    public static void main(String[] args) {
        LinkedListDeque<Integer> L = new LinkedListDeque<>(5);
        L.addFirst(10);
        L.addFirst(20);
        L.addLast(8);
        L.printDeque();
        System.out.println(L.get(2));
        System.out.println(L.getRecursive(4));
    }
     */
}
