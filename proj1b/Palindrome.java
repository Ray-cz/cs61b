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

    public boolean isPalindrome(String word, CharacterComparator cc) {
        int length = word.length();
        for (int i = 0; i < length / 2; i++) {
            if (!cc.equalChars(word.charAt(i), word.charAt(length - 1 - i))) {
                return false;
            }
        }
        return true;
    }
}
