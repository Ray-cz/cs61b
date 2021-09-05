import java.util.*;

public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void insert(String s) {
        TrieNode curr = root;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!curr.children.containsKey(c)) {
                curr.children.put(c, new TrieNode(c));
            }
            curr = curr.children.get(c);
        }
        curr.isWord = true;
    }

    public List<String> prefixMatch(String prefix) {
        TrieNode n = root;
        List<String> res = new ArrayList<>();
        for (int i = 0; i < prefix.length() - 1; i++) {
            char c = prefix.charAt(i);
            n = n.children.get(c);
            if (n == null) {
                // No matching result
                return res;
            }
        }
        colHelp(res, prefix, n);
        return res;
    }

    private void colHelp(List<String> x, String s, TrieNode n) {
        if (n.isWord) {
            x.add(s);
        } else {
            for (char c : n.children.keySet()) {
                colHelp(x, s + c, n.children.get(c));
            }
        }
    }

    static class TrieNode {
        char c;
        boolean isWord;
        Map<Character, TrieNode> children;

        public TrieNode() {
            isWord = false;
            children = new HashMap<>();
        }
        public TrieNode(char c) {
            this.c = c;
            isWord = false;
            children = new HashMap<>();
        }
    }

}
