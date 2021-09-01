/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    static final int RADIX = 256;
    public static String[] sort(String[] asciis) {
        int maxLength = Integer.MIN_VALUE;
        for (String s : asciis) {
            maxLength = Math.max(maxLength, s.length());
        }
        String[] res = asciis.clone();
        for (int i = maxLength - 1; i >= 0; i--) {
            res = sortHelperLSD(asciis, i);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static String[] sortHelperLSD(String[] asciis, int index) {
        int counts[] = new int[RADIX];
        for (String s : asciis) {
            if (index > s.length() - 1) {
                counts[0]++;
            } else {
                counts[(int)s.charAt(index)]++;
            }
        }

        int starts[] = new int[RADIX];
        int pos = 0;
        for (int i = 0; i < starts.length; i++) {
            starts[i] = pos;
            pos += counts[i];
        }

        String[] sorted = new String[asciis.length];
        for (String s : asciis) {
            if (index > s.length() - 1) {
                sorted[starts[0]] = s;
                starts[0]++;
            } else {
                int place = starts[(int)s.charAt(index)];
                sorted[place] = s;
                starts[(int)s.charAt(index)]++;
            }
        }
        return sorted;
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }

    public static void main(String[] args) {
        String[] test = {"Ava", "Diana", "Bella", "Eileen", "Carol", "Nanami", "Dina", "Avavaava",
                        "Aileen", "Kira", "Q", "Queen"};
        String[] res = sort(test);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
