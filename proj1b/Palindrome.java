public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> d = new LinkedListDeque<>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }
        return d;
    }


    private boolean isPalindrome(Deque d) {
        if (d.size() == 0 || d.size() == 1) {
            return true;
        }
        if (d.removeFirst() != d.removeLast()) {
            return false;
        }
        return isPalindrome(d);
    }
    public boolean isPalindrome(String word) {
        Deque d = wordToDeque(word);
        return isPalindrome(d);
    }
}
