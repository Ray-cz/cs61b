package synthesizer;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        first = last = fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        fillCount += 1;
        rb[last] = x;
        last = (last + 1) % capacity;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount -= 1;
        T result = rb[first];
        rb[first] = null;
        first = (first + 1) % capacity;
        return result;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];
    }

    private class BufferIterator implements Iterator<T> {
        private int count;
        private int wizPos;
        public BufferIterator() {
            wizPos = first;
            count = 0;
        }

        @Override
        public boolean hasNext() {
            return count < fillCount;
        }

        @Override
        public T next() {
            T result = rb[wizPos];
            count += 1;
            wizPos = (wizPos + 1) % capacity;
            return result;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BufferIterator();
    }

}
