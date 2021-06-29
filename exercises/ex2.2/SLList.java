
/** An SLList is a list of integers, which hides the terrible truth
 * of the nakedness within. */
public class SLList {
    private static class IntNode {
        public int item;
        public IntNode next;

        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
            //System.out.println(size);
        }
    }

    /* The first item (if it exists) is at sentinel.next. */
    private IntNode sentinel;
    private static int size;



    /** Creates an empty SLList. */
    public SLList() {
        sentinel = new IntNode(63, null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(63, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }

    public SLList(int[] x){
        sentinel = new IntNode(64, null);
        size = 1;
        for (int i = 0; i < x.length; i++) {
            addLast(x[i]);
            size = size + 1;
        }
    }

    /** Adds x to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size = size + 1;
    }

    /**Deletes x from the front of the list */
    public void deleteFirst(){
        sentinel.next = sentinel.next.next;
        size = size - 1;
    }

    /** Returns the first item in the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds x to the end of the list. */
    public void addLast(int x) {
        size = size + 1;

        IntNode p = sentinel;

        /* Advance p to the end of the list. */
        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntNode(x, null);
    }

    /** Returns the size of the list. */
    public int size() {
        return size;
    }

    /** Add two same numbers together and make one large node. 
     * For example:1→1→2→3 becomes 2→2→3 which becomes 4→3 */
    public void addAdjacent(){
        IntNode p = sentinel;
        while(p.next.next != null){
            if(p.next.item == p.next.next.item){
                p.next.next.item *= 2;
                p.next = p.next.next;
            }
            else
                p = p.next;
        }
    }

    /**Adds a value and squares the list.
     * For example, upon the insertion of 5, the below list would be:
     * 1=>2 to 1=>1=>2=>4=>5
     * and if 7 was added to the latter list, it would be:
     * 1=>1=>1=>1=>2=>4=>4=>16=>5=>25=>7
     * Additional constraint:size() function can only be accessed once.
     */
    public void addAndSquare(int x){
        addLast(x);
        IntNode p = sentinel;
        while(p.next != null){
           int temp = p.next.item;
           p = p.next;
           p.next = new IntNode(temp*temp, p.next);
           p = p.next;
        }
    }


    public static void main(String[] args) {
        /* Creates a list of one integer, namely 10 */
        int[] x = { 1, 1, 2, 4, 5 };
        SLList L = new SLList(x);
        L.addAndSquare(7);
        L.addAdjacent();
    }
}