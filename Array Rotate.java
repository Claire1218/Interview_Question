package Phone_Screen;

//[1,2,3,4,5,6] when k = 2 --> 5,6,1,2,3,4
public class ArrayRotation {
	//time complexity O(kn);
	//space complexity O(1)
	/*[1,2,3,4], k = 2;
	 * (1): temp = 4 --> arr = [1, 1, 2, 3] -- > [4, 1, 2, 3]
	 * (2): temp = 3 --> arr = [4, 4, 1, 2] --> [3, 4, 1, 2]
	 * */
	public void arrayRotation(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k == arr.length)
			return;
		k = k % arr.length;
		for (int i = 1; i <= k; i++) {
			int temp = arr[arr.length - 1];
			for (int j = arr.length - 1; j > 0; j--) {
				arr[j] = arr[j - 1];
			}
			arr[0] = temp;
		}
	}
	//juggling algorithm
	//time complexity O(n)
	//space complexity O(1)
	/*[1, 2, 3, 4] --> [3, 2, 1, 4] -- > [3, 4, 1, 2]
	 * move k steps within arr.length
	 * */
	public void arrayRotation1(int[] arr, int k) {
		if (arr == null || arr.length == 0 || k == arr.length)
			return;
		k = arr.length - k % arr.length;
		for (int i = 0; i < gcd(k, arr.length); i++) {
			int temp = arr[i];
			int j = i;
			while (j < arr.length) {
				int d = (j + k) % arr.length;
				if (d == i)
					break;
				arr[j] = arr[d];
				j = d;
			}
			arr[j] = temp;
		}
	}
	private int gcd(int a, int b) {
		if (b == 0)
			return a;
		else
			return gcd(b, a % b);
	}
	public static void main(String[] args) {
		ArrayRotation test = new ArrayRotation();
		int[] arr = new int[] {1, 2, 3, 4, 5, 6, 7};
		int k = 5;
		test.arrayRotation(arr, k);
		for (int i = 0; i < arr.length; i++) {
			System.out.print(arr[i] + " ");
		}
		System.out.println();
		
		int[] arr1 = new int[] {1, 2, 3, 4, 5, 6, 7};
		test.arrayRotation1(arr1, k);
		for (int i = 0; i < arr1.length; i++) {
			System.out.print(arr1[i] + " ");
		}
		System.out.println();
	}

}
