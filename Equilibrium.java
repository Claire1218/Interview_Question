package Phone_Screen;

public class Equilibrium {
	public static int equilibrium(int[] arr) { //time complexity O(n ^ 2)
		if (arr == null || arr.length == 0)
			return -1;
		for (int i = 0; i < arr.length; i++) {
			int leftSum = 0;
			int rightSum = 0;
			//time complexity O(n)
			for (int k1 = 0; k1 < i; k1++) {
				leftSum += arr[k1];
			}
			for (int k2 = i + 1; k2 < arr.length; k2++) {
				rightSum += arr[k2];
			}
			if (leftSum == rightSum)
				return i;
		}
		return -1;
	}
	
	//solution2: time complexity O(n)
	public static int equilibrium_update(int[] arr) {
		if (arr == null || arr.length == 0)
			return -1;
		int sum = 0;
		int leftSum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
		}
		for (int i = 0; i < arr.length; i++) {
			sum -= arr[i];
			if (sum == leftSum)
				return i;
			leftSum += arr[i];
		}
		return -1;
	}

}
