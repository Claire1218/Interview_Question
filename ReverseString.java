package Phone_Screen;

public class ReverseString {
	//case 1: only reverse string and omit other characters
	//time complexity O(n);
	//space compleixty O(n)
	//solution1: using string split method
	public String reverseString(String str) {
		if (str == null || str.length() == 0)
			return "";
		str = str.trim();
		String[] arr = str.split("\\s+");
		StringBuilder res = new StringBuilder();
		for (int i = arr.length - 1; i >= 0; i--) {
			res.append(arr[i]);
			if (i > 0)
				res.append(" ");
		}
		return res.toString();
	}
	//solution2: without using string split method, or you can change to use count to make space as O(1)
	public String reverseString1(String str) {
		if (str == null || str.length() == 0)
			return "";
		StringBuilder res = new StringBuilder();
		StringBuilder cur = new StringBuilder();
		int i = str.length() - 1;
		while (i >= 0) {
			if (str.charAt(i) == ' ') {
				if (cur.length() > 0) {
					res.append(cur.reverse()).append(" ");
					cur = new StringBuilder();
				}
				while (i >= 0 && str.charAt(i) == ' ')
					i--;
			}
			else {
				cur.append(str.charAt(i));
				i--;
			}
		}
		if (cur.length() > 0)
			res.append(cur.reverse());
		return res.toString();
	}
	
	//case2: save all characters inside  string
	public boolean isValid(char c) {
		if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c >= '0' && c <= '9')
			return true;
		return false;
	}
	//solution1: using string builder
	//time complexity O(n)
	//space complexity O(n) in worst case;
	public String reverseString2_1(String str) {
		if (str == null || str.length() == 0)
			return "";
		StringBuilder res = new StringBuilder();
		StringBuilder cur = new StringBuilder();
		for (int i = str.length() - 1; i >= 0; i--) {
			if (isValid(str.charAt(i))) {
				cur.append(str.charAt(i));
			}
			else {
				if (cur.length() > 0) {
					res.append(cur.reverse());
					cur = new StringBuilder();
				}
				res.append(str.charAt(i));
			}
		}
		if (cur.length() > 0) {
			res.append(cur.reverse());
		}
		return res.toString();
	}
	//solution2: space O(1), except for res as the output space
	//time complexity O(n);
	public String reverseString2_2(String str) {
		if (str == null || str.length() == 0)
			return "";
		StringBuilder res = new StringBuilder();
		int count = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			if (str.charAt(i) == ' ') {
				if (count > 0) {
					res.append(str.substring(i + 1, i + count + 1));
					count = 0;
				}
				res.append(str.charAt(i));
			}
			else {
				count++;
			}
		}
		return res.toString();
	}
	
	public static void main(String[] args) {
		ReverseString test = new ReverseString();
		String str = " such ,  a wonderful land  ";
		System.out.println(test.reverseString(str));
		System.out.println(test.reverseString1(str));
		System.out.println(test.reverseString2_1(str));
		System.out.println(test.reverseString2_2(str));
	}

}
