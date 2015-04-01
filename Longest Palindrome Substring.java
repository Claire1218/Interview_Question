package Phone_Screen;

public class LongestPalindrome {
	//solution1: bruth force
	//time complexity O(n ^ 2);
	//space complexity O(n ^ 2);
	public String longestPalin(String str) {
		if (str == null || str.length() == 0) 
			return str;
		String max = "";
		for (int i = 0; i < str.length() * 2; i++) {
			int left = i / 2;
			int right = i / 2;
			if (i % 2 == 1)
				right++;
			String cur = isPalin(str, left, right);
			if (cur.length() > max.length()) {
				max = cur;
			}
		}
		return max;
	}
	private String isPalin(String str, int left, int right) { 
		while (left >= 0 && right < str.length() && str.charAt(left) == str.charAt(right)) {
			left--;
			right++;
		}
		return str.substring(left + 1, right);
	}
	//solution1: dynamic programming
	//time complexity O(n ^ 2)
	//space complexity O(n ^ 2)
	public String longestPalin1(String str) {
		if (str == null || str.length() == 0)
			return "";
		int maxCount = 0;
		int maxLeft = -1;
		boolean[][] checker = new boolean[str.length()][str.length()];
		for (int i = str.length() - 1; i >= 0; i--) {
			for (int j = i; j < str.length(); j++) {
				if (str.charAt(i) == str.charAt(j) && (j - i <= 2 || checker[i + 1][j - 1])) {
					checker[i][j] = true;
					if (j - i + 1 > maxCount) {
						maxCount = j - i + 1;
						maxLeft = i;
					}
				}
			}
		}
		return str.substring(maxLeft, maxLeft + maxCount);
	}
	
	
	public static void main(String[] args) {
		LongestPalindrome test = new LongestPalindrome();
		String str = "aabbccbbacac";
		System.out.println(test.longestPalin(str));
		System.out.println(test.longestPalin1(str));
	}
	
	
}
