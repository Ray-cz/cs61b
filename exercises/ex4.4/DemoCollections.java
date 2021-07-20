import java.util.*;
import java.io.InputStream;

public class DemoCollections {

	public static String cleanString(String s) {
		return s.toLowerCase().replaceAll("[^a-z]", "");
	}

	public static List<String> getWords(String inputFileName) {
		List<String> lst = new ArrayList<String>();
		In in = new In(inputFileName);
		while (!in.isEmpty()) {
			String nextWord = cleanString(in.readString());
			System.out.println(nextWord);
			lst.add(nextWord);
		}
		return lst;
	}

	public static int countUniqueWords(List<String> words) {
		Set<String> wordSet = new HashSet<>();
		for (String ithWords : words) {
			wordSet.add(ithWords);
		}
		return wordSet.size();
	}

	public static Map<String, Integer> collectWordCount(List<String> words, List<String> target) {
		Map<String, Integer> counts = new HashMap<>();
		for (String t: target) {
			counts.put(t, 0);
		}
		for (String word: words) {
			if (counts.containsKey(word)) {
				counts.put(word, counts.get(word) + 1);
			}
		}
		return counts;
	}

	public static void main(String[] args) {
		String fileName = "demo.txt";
		List<String> lst = getWords(fileName);
		System.out.println(countUniqueWords(lst));

		List<String> target = new ArrayList<>();
		target.add("hello");
		target.add("world");
		target.add("what");
		System.out.println(collectWordCount(lst, target));
	}
}