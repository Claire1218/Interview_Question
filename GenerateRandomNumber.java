package Phone_Screen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class GenerateRandomNumber {
	private int counter = 0;
	private Random rd = new Random();
	private List<Integer> randomList;
	private final int CAPACITY;
	
	public GenerateRandomNumber(int capacity) { // time complexity O(n), n is the capacity
		if (capacity <= 0 || capacity > Integer.MAX_VALUE)
			throw new IllegalArgumentException("invalid input parameter");
		//generate random pool
		CAPACITY = capacity;
		HashSet<Integer> pool = new HashSet<Integer>();
		for (int top = Integer.MAX_VALUE - capacity; top < Integer.MAX_VALUE; top++) {
			if (!pool.add(rd.nextInt(top + 1))) {
				pool.add(rd.nextInt(top + 1));
			}
		}
		//transfer random pool to array
		randomList = new ArrayList<Integer>(pool);
		//shuffle array in random order
		for (int i = randomList.size() - 1; i >= 0; i--) {
			int k = rd.nextInt(i + 1);
			Integer temp = randomList.get(k);
			randomList.set(k, randomList.get(i));
			randomList.set(i, temp);
		}
	}
	public Integer getRandom() {
		if (counter > CAPACITY)
			throw new IndexOutOfBoundsException("already reach the upper bounds");
		return randomList.get(counter++);
	}
	public static void main(String[] args) {
		int size = 5;
		GenerateRandomNumber rd = new GenerateRandomNumber(size);
		for (int i = 0; i < size; i++) {
			System.out.println(rd.getRandom());
		}
	}
	

}
