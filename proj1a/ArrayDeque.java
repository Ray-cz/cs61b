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

    /**helper functions*/
    private int advanceOne(int next) {
        return Math.floorMod(next + 1, capacity);
    }
    private int backOne(int next) {
        return Math.floorMod(next - 1, capacity);
    }

    private void resize(int cap) {
        T[] a = (T[]) new Object[cap];
        int index = advanceOne(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[index];
            index = advanceOne(index);
        }
        items = a;
        capacity = cap;
        nextFirst = capacity - 1;
        nextLast = size;
    }

    private void checkSize() {
        double usage = 1.0 * size / capacity;
        if (capacity < 16 || usage >= 0.25 || isEmpty()) {
            return;
        }
        resize(size * 4);
    }

    public void addFirst(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        size += 1;
        items[nextFirst] = item;
        nextFirst = backOne(nextFirst);
    }

    public void addLast(T item) {
        if (isFull()) {
            resize(size * 2);
        }
        size += 1;
        items[nextLast] = item;
        nextLast = advanceOne(nextLast);
    }

    public void printDeque() {
        int index = advanceOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[index]);
            System.out.print(' ');
            index = advanceOne(index);
        }
        System.out.println();
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextFirst = advanceOne(nextFirst);
        T result = items[nextFirst];
        items[nextFirst] = null;
        checkSize();
        return result;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size -= 1;
        nextLast = backOne(nextLast);
        T result = items[nextLast];
        items[nextLast] = null;
        checkSize();
        return result;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int first = advanceOne(nextFirst);
        return items[Math.floorMod(first + index, capacity)];
    }
}
