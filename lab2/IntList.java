public class IntList {
    public int first;
    public IntList rest;

    public IntList(int f, IntList r) {
        first = f;
        rest = r;
    }

    /** Return the size of the list using... recursion! */
    public int size() {
        if (rest == null) {
            return 1;
        }
        return 1 + this.rest.size();
    }

    /** Return the size of the list using no recursion! */
    public int iterativeSize() {
        IntList p = this;
        int totalSize = 0;
        while (p != null) {
            totalSize += 1;
            p = p.rest;
        }
        return totalSize;
    }

    /** Returns the ith item of this IntList. */
    public int get(int i) {
        if (i == 0) {
            return first;
        }
        return rest.get(i - 1);
    }

    /** Returns an IntList identical to L, but with all values incremented by x.
     Values in L cannot change! */
    public static IntList incrList(IntList L, int x){
        if(L.rest == null){
            return new IntList(L.first + x, null);
        }
        return new IntList(L.first + x, incrList(L.rest, x));
    }

    /** Returns an IntList identical to L, but with all values incremented by x.
     Not allowed to use ‘new’ (to save memory). */
    public static IntList dincrList(IntList L, int x){
        IntList dL = L;
        while(dL.rest != null){
            dL.first += x;
            dL = dL.rest;
        }
        dL.first += x;
        return L;
    }

    public static void main(String[] args) {
        IntList L = new IntList(15, null);
        L = new IntList(10, L);
        L = new IntList(5, L);

        System.out.println(L.get(0));
        IntList incrL = incrList(L, 2);
        System.out.println(incrL.get(0));

    }
}