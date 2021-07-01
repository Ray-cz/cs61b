public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int capacity;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
        nextFirst = 7;
        nextLast = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        System.arraycopy(items, 0, a, 0, nextLast);
        System.arraycopy(items, nextFirst + 1, a, nextLast + size
                , capacity - nextLast);
        nextFirst += size;
        capacity = cap;
        items = a;
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size*2);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = (nextFirst - 1) % capacity;
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(size*2);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = (nextLast + 1) % capacity;
    }

    public void printDeque() {
        for(int i = nextFirst + 1; i < capacity; i++) {
            System.out.print(items[i]);
            System.out.print(' ');
        }
        for(int i = 0; i < nextLast; i++) {
            System.out.print(items[i]);
            System.out.print(' ');
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T result = items[nextFirst + 1];
        items[nextFirst + 1] = null;
        nextFirst = (nextFirst + 1) % capacity;
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        T result = items[nextLast - 1];
        items[nextLast - 1] = null;
        nextLast = (nextLast - 1) % capacity;
        return result;
    }

    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        if (nextFirst + 1 + index < capacity) {
            return items[nextFirst + 1 + index];
        }
        return items[nextLast - (size - index)];
    }


    public static void main(String[] args) {
        ArrayDeque<Integer> A = new ArrayDeque();
        A.addFirst(1);
        A.addFirst(2);
        A.addLast(3);
        A.addLast(5);
        A.addFirst(4);
        A.addFirst(4);
        A.addFirst(4);
        A.addFirst(4);
        A.addFirst(9);

        A.printDeque();
    }

}
