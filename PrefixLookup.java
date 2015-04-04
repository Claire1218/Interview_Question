package Phone_Screen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PrefixLookUp {
	
	public static void main(String[] args) {
		List<String> prefix = new ArrayList<String>();
		prefix.add("app");
		prefix.add("pre");
		List<String> words = new ArrayList<String>();
		words.add("apple");
		words.add("application");
		words.add("previous");
		words.add("prepare");
		
		Trie trie = new Trie(prefix, words);
		for (int i = 0; i < prefix.size(); i++) {
			List<String> matchWords = (List<String>) trie.getPrefixMatching(prefix.get(i));
			if (matchWords != null) {
				for (int j = 0; j < matchWords.size(); j++) {
					System.out.print(matchWords.get(j) + " ");
				}
				System.out.println();
			}
		}
		
//		trie.addPrefix("appl");
//		prefix.add("appl");
//		for (int i = 0; i < prefix.size(); i++) {
//			List<String> matchWords = (List<String>) trie.getPrefixMatching(prefix.get(i));
//			if (matchWords != null) {
//				for (int j = 0; j < matchWords.size(); j++) {
//					System.out.print(matchWords.get(j) + " ");
//				}
//				System.out.println();
//			}
//		}
	}
	
}

class Trie {
	private TrieNode root;
	private final int OFFSET = 97;
	
	
	private List<String> prefixList;
	private List<String> wordList;
	private HashMap<String, List<String>> mapping;

	public Trie() {
		root = new TrieNode('\0');
		
		prefixList = new ArrayList<String>();
		wordList = new ArrayList<String>();
		
		mapping = new HashMap<String, List<String>>();
	}
	public Trie(List<String> inputPrefix, List<String> inputWords) { // O(longestString.length)
		this();
		if (inputPrefix != null) {
			for (int i = 0; i < inputPrefix.size(); i++) {
				if (inputWords.get(i) != null || inputWords.get(i).length() != 0)
					addPrefix(inputPrefix.get(i));
			}
		}
		if (inputWords != null) {
			for (int i = 0; i < inputWords.size(); i++) {
				addWords(inputWords.get(i));
			}
		}
	}
	public void addPrefix(String prefix) { // time complexity: O(prefix.length() + O (nm) --> O(nm);
		if (prefix == null || prefix.length() == 0)
			return;
		if (prefixList.contains(prefix))
			return;
		prefixList.add(prefix);
		
		//1. update in the trie tree
		TrieNode temp = root;
		for (int i = 0; i < prefix.length(); i++) {
			if (temp.getChildren()[(int) prefix.charAt(i) - OFFSET] == null) {
				temp.getChildren()[(int) prefix.charAt(i) - OFFSET] = new TrieNode(prefix.charAt(i));
			}
			temp = temp.getChildren()[(int) prefix.charAt(i) - OFFSET];
		}
		temp.setPrefix(true);
		
		//2.get all words with current prefix string
		
		StringBuilder curWord = new StringBuilder();
		curWord.append(prefix);
		List<String> relatedWords = new ArrayList<String>();
		getAllWords(temp, curWord, relatedWords); // time complexity O(nm);
		
		//3. update into hashmap // O(1)
		mapping.put(prefix, relatedWords);
	}
	
	public void addWords(String word) { // time complexity O(word.length())
		if (word == null || word.length() == 0)
			return;
		if (wordList.contains(word))
			return;
		wordList.add(word);
		
		TrieNode temp = root;
		for (int i = 0; i < word.length(); i++) {
			if (temp.getChildren()[(int) word.charAt(i) - OFFSET] == null)
				temp.getChildren()[(int) word.charAt(i) - OFFSET] = new TrieNode(word.charAt(i));
			if (temp.getChildren()[(int) word.charAt(i) - OFFSET].isPrefix()) {
				mapping.get(word.substring(0, i + 1)).add(word);
			}
			temp = temp.getChildren()[(int) word.charAt(i) - OFFSET];
		}
		temp.setFullWord(true);
	}
	
	public Iterable<String> getPrefixMatching(String prefix) { // time complexity is O(1)
		if (!mapping.containsKey(prefix))
			return null;
		return mapping.get(prefix);
	}
	private void getAllWords(TrieNode node, StringBuilder cur, List<String> res) { // O(m * n): m is the longest length of matched words and n is the number of matched words
		if (node == null)
			return;
		if (node.isFull()) {
			res.add(cur.toString());
		}
		for (TrieNode child: node.getChildren()) {
			if (child != null) {
				cur.append(child.getLetter());
				getAllWords(child, cur, res);
				cur.delete(cur.length() - 1, cur.length());
			}
		}
	}
}


class TrieNode {
	private boolean isPrefix;
	private boolean isFullWord;
	private char letter;
	private TrieNode[] children;
	
	public TrieNode(char letter) {
		this.letter = letter;
		children = new TrieNode[26];
	}
	public char getLetter() {
		return letter;
	}
	public TrieNode[] getChildren() {
		return children;
	}
	public void setPrefix(boolean isPrefix) {
		this.isPrefix = isPrefix;
	}
	public void setFullWord(boolean isFullWord) {
		this.isFullWord = isFullWord;
	}
	public boolean isPrefix() {
		return isPrefix;
	}
	public boolean isFull() {
		return isFullWord;
	}
}

