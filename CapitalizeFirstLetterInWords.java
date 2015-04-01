package Phone_Screen;

public class CapitalizeFirstLetterInWords {
	//time complexity O(k) --> k is the number of words inside this sentence;
	//space complexity: O(n) --> words and letters array to store tempory output
	public String capitalizeFirstLetter(String sentence) {
		if (sentence == null)
			return "";
		sentence = sentence.trim();
		if (sentence.length() == 0)
			return "";
		String[] words = sentence.split("\\s+");
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < words.length; i++) {
			String curWord = words[i];
			char[] letters = curWord.toCharArray();
			if (isValid(letters[0])) {
				letters[0] = (char) (letters[0] - 'a' + 'A');
			}
			String newWord = new String(letters);
			res.append(newWord);
			if (i < words.length - 1)
				res.append(" ");
		}
		return res.toString();
	}
	private boolean isValid(char c) {
		if (c >= 'a' && c <= 'z')
			return true;
		return false;
	}
	public static void main(String[] args) {
		CapitalizeFirstLetterInWords test = new CapitalizeFirstLetterInWords();
		String str = "wonderful world i really like !! i love that! GOOD";
		System.out.println(test.capitalizeFirstLetter(str));
	}
	

}
