/*ATTENTION: STILL HAS BUG INSIDE*/

import java.math.BigDecimal;

public class CoinsCalculate {
	//main function
	public static void main(String[] arge) {
		//pass test cases
		int money = 10;
		int[] denomination = new int[]{1, 2, 3, 4, 5};
		System.out.println(minChange_recursive(money, denomination));
		System.out.println(minChange_dp(money, denomination));
		
		double money1 = 6.2;
		double[] denomination1 = new double[]{0.3, 2.2};
		System.out.println(minChange_recursive2(money1, denomination1));
		System.out.println(minChange_dp(money1, 0.1, denomination1));
	}
	//case1: divide and conqure
	
	//senario1: interger input
	public static int minChange_recursive(int money, int[] denomination) {
		if (money == 0)
			return 0;
		
		int[] preCoins = new int[denomination.length];
		int[] curCoins = new int[denomination.length];
		for (int i = 0; i < denomination.length; i++) {
			preCoins[i] = -1;
			curCoins[i] = -1;
		}
		
		for (int k = 0; k < denomination.length; k++) {
			if (denomination[k] <= money) {
				preCoins[k] = minChange_recursive(money - denomination[k], denomination);
				if (preCoins[k] >= 0) {
					curCoins[k] = preCoins[k] + 1;
				}
			}
		}
		int finalCoins = -1;
		for (int k = 0; k < denomination.length; k++) {
			if (curCoins[k] >= 0) {
				if (finalCoins == -1 || finalCoins > curCoins[k]) {
					finalCoins = curCoins[k];
				}
			}
		}
		return finalCoins;
	}
	
	//senario2: double input
	public static int minChange_recursive2(double money, double[] denomination) {
		BigDecimal money1 = BigDecimal.valueOf(money);
		BigDecimal[] denomination1 = new BigDecimal[denomination.length];
		for (int i = 0; i < denomination.length; i++) {
			denomination1[i] = BigDecimal.valueOf(denomination[i]);
		}
		return helper_recursive2(money1, denomination1);
		
	}
	private static int helper_recursive2(BigDecimal money, BigDecimal[] denomination) {
		if (money.compareTo(BigDecimal.ZERO) == 0)
			return 0;
		int[] preCounts = new int[denomination.length];
		int[] curCounts = new int[denomination.length];
		for (int i = 0; i < denomination.length; i++) {
			preCounts[i] = -1;
			curCounts[i] = -1;
		}
		
		for (int k = 0; k < denomination.length; k++) {
			if (money.compareTo(denomination[k]) >= 0) {
				preCounts[k] = helper_recursive2(money.subtract(denomination[k]), denomination);
				if (preCounts[k] >= 0)
					curCounts[k] = preCounts[k] + 1;
				
			}
		}
		int finalCounts = -1;
		for (int i = 0; i < denomination.length; i++) {
			if (curCounts[i] >= 0) {
				if (finalCounts == -1 || finalCounts > curCounts[i])
					finalCounts = curCounts[i];
			}
		}
		return finalCounts;
	}
	
	
	//case2: dynamic programming
	//senario1: integer input
	public static int minChange_dp(int money, int[] denomination) {
		if (money <= 0)
			return 0;
		int[] usedCoins = new int[money + 1];
		usedCoins[0] = 0;
		for (int cents = 1; cents <= money; cents++) {
			int minCoins = cents;
			for (int i = 0; i < denomination.length; i++) {
				if (denomination[i] <= cents) {
					int temp = usedCoins[cents - denomination[i]] + 1;
					if (temp < minCoins)
						minCoins = temp;
				}
			}
			usedCoins[cents] = minCoins;
		}
		return usedCoins[money];
	}
	
	//senario2: double integer
	public static int minChange_dp(double money, double base, double[] denomination) {
		BigDecimal money1 = BigDecimal.valueOf(money);
		BigDecimal base1 = BigDecimal.valueOf(base);
		int count = money1.divide(base1).intValue();
		BigDecimal[] denomination1 = new BigDecimal[denomination.length];
		for (int i = 0; i < denomination.length; i++) {
			denomination1[i] = BigDecimal.valueOf(denomination[i]);
		}
		return helper_dp(money1, base1, count, denomination1);
	}
	private static int helper_dp(BigDecimal money, BigDecimal base, int count, BigDecimal[] denomination) {
		if (money.compareTo(BigDecimal.ZERO) <= 0)
			return 0;
		int[] usedCoins = new int[count + 1];
		usedCoins[0] = 0;
		for (int i = 1; i <= count; i++) {
			BigDecimal curMoney = base.multiply(BigDecimal.valueOf(i));
			int minCoins = i;
			for (int k = 0; k < denomination.length; k++) {
				if (curMoney.compareTo(denomination[k]) >= 0) {
					int index = ((curMoney.subtract(denomination[k])).divide(base)).intValue();
					int temp = usedCoins[index] + 1;
					if (minCoins > temp)
						minCoins = temp;
				}
			}
			usedCoins[i] = minCoins;
		}
		return usedCoins[count];
	}
}
