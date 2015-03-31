import java.util.NoSuchElementException;

public class Kth_smallest_twoSortedArrays {
	//solution1: union and find 
	//time complexity: O(n + m): n and m are the number of items in two sorted arrays
	//space complexity: O(n + m)
	public int kthSmallestItem(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr1.length == 0) {
			if (arr2 == null || k > arr2.length)
				throw new NoSuchElementException();
			return arr1[k - 1];
		}
		if (arr2 == null || arr2.length == 0) {
			if (arr1 == null || k > arr1.length)
				throw new NoSuchElementException();
			return arr2[k - 1];
		}
		if (k > arr1.length + arr2.length)
			throw new NoSuchElementException();
		
		int[] combine = new int[arr1.length + arr2.length];
		int index1 = 0;
		int index2 = 0;
		int index = 0;
		while (index1 < arr1.length && index2 < arr2.length) {
			if (arr1[index1] < arr2[index2]) {
				combine[index++] = arr1[index1++];
			}
			else {
				combine[index++] = arr2[index2++];
			}
		}
		while (index1 < arr1.length) {
			combine[index++] = arr1[index1++];
		}
		while (index2 < arr2.length) {
			combine[index++] = arr2[index2++];
		}
		return combine[k - 1];
 	}
	
	//solution2: iterate and find
	//time complexity O(k); --> O(m + n) in worst case
	//space complexity O(1);
	public int kthSmallestItem1(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr1.length == 0) {
			if (arr1 == null || k > arr2.length)
				throw new NoSuchElementException();
			return arr2[k - 1];
		}
		if (arr2 == null || arr2.length == 0) {
			if (arr1 == null || k > arr1.length)
				throw new NoSuchElementException();
			return arr1[k - 1];
		}
		if (k > arr1.length + arr2.length)
			throw new NoSuchElementException();
		int index1 = 0;
		int index2 = 0;
		while (index1 < arr1.length && index2 < arr2.length) {
			if (index1 + index2 + 1 == k)
				return Math.min(arr1[index1], arr2[index2]);
			if (arr1[index1] < arr2[index2])
				index1++;
			else
				index2++;
		}
		if (index1 < arr1.length) {
			return arr1[k - index2 - 1];
		}
		else {
			return arr2[k - index1 - 1];
		}
	}
	
	//solution3: binary search and find
	//time complexity: O(logk)
	//space complexity O(logk) --> recursive stack space
	public int kthSmallestItem2(int[] arr1, int[] arr2, int k) {
		if (arr1 == null || arr1.length == 0) {
			if (arr1 == null || k > arr2.length)
				throw new NoSuchElementException();
			return arr2[k - 1];
		}
		if (arr2 == null || arr2.length == 0) {
			if (arr1 == null || k > arr1.length)
				throw new NoSuchElementException();
			return arr1[k - 1];
		}
		if (k > arr1.length + arr2.length)
			throw new NoSuchElementException();
		return helper(arr1, 0, arr1.length - 1, arr2, 0, arr2.length - 1, k);
	}
	private int helper(int[] arr1, int l1, int r1, int[] arr2, int l2, int r2, int k) {
		int len1 = r1 - l1 + 1;
		int len2 = r2 - l2 + 1;
		if (len1 > len2) 
			return helper(arr2, l2, r2, arr1, l1, r1, k);
		if (k == 1)
			return Math.min(arr1[l1], arr2[l2]);
		if (len1 == 0)
			return arr2[l2 + k - 1];
		
		int pos1 = Math.min(len1, k / 2);
		int pos2 = k - pos1;
		if (arr1[l1 + pos1 - 1] == arr2[l2 + pos2 - 1])
			return arr1[l1 + pos1 - 1];
		else if (arr1[l1 + pos1 - 1] < arr2[l2 + pos2 - 1]) 
			return helper(arr1, l1 + pos1, r1, arr2, l2, l2 + pos2 - 1, k - pos1);
		else 
			return helper(arr1, l1, l1 + pos1 - 1, arr2, l2 + pos2, r2, k - pos2);
		
	}
	
	public static void main(String[] args) {
		Kth_smallest_twoSortedArrays test = new Kth_smallest_twoSortedArrays();
		int[] arr1 = new int[]{1,5,8,10};
		int[] arr2 = new int[]{3,7,12};
		for (int k = 1; k <= 7; k++) {
			System.out.println(test.kthSmallestItem(arr1, arr2, k));
			System.out.println(test.kthSmallestItem1(arr1, arr2, k));
			System.out.println(test.kthSmallestItem2(arr1, arr2, k));
			System.out.println("!!!!");
		}
	}
	
}
