package Phone_Screen;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Iterator;

/*PRE_DISCUSSION:
 * Is the collection random access (e.g. ArrayList)?
 * Is the collection read only?
 * Do we allow random running time?
 * Is N known at invocation time, or are we dealing with a stream of items of unknown size?
 * */
public class RandomProblem {
	
	/*case1: Trial and Error;
	 * There is a collection with the given size, randomly pick m items from that collection and add them to the result collection
	 * Time complexity is O(m) --> linear algorithm
	 * las vegas algorithm
	 * */
	private static Random rd = new Random();
	public static <T> Set<T> randomSample1(List<T> items, int m) {
		Set<T> res = new HashSet<T>();
		int n = items.size();
		if (m > n / 2) {
			Set<T> negativeSet = randomSample1(items, n - m);
			for (T item: items) {
				if (!negativeSet.contains(item)) {
					res.add(item);
				}
			}
		}
		else {
			while (res.size() < m) {
				int index = rd.nextInt(n);
				res.add(items.get(index));
			}
		}
		return res;
	}
	
	/*case2: SWAPPING
	 * If our collection is random access and its items can be freely reordered, 
	 * then we can efficiently draw random items one by one from a candidates set, containing only items not chosen so far.
	 * time complexity O(m)
	 * --> knuth shuffle
	 * */
	public static <T> List<T> randomSample2(List<T> items, int m) {
		for (int i = 0; i < m; i++) {
			int pos = i + rd.nextInt(items.size() - i);
			T temp = items.get(pos);
			items.set(pos, items.get(i));
			items.set(i, temp);
		}
		return items.subList(0, m);
	}
	/*case3: FULL SCAN
	 * Sometimes our collection is not random access, so we have no choice but to traverse it completely in the worst case. 
	 * Following is an elegant solution, that iterates once through the items, 
	 * and selects an item with probability (#remaining to select)/(#remaining to scan): 
	 * */
	public static <T> List<T> randomSample3(List<T> items, int m) {
		List<T> res = new ArrayList<T>();
		int visited = 0;
		Iterator<T> it = items.iterator();
		while (m > 0) {
			T item = it.next();
			if (rd.nextDouble() < (m / (items.size() - visited))) {
				res.add(item);
				m--;
			}
			visited++;
		}
		return res;
	}
	
	/*case4: Floyd's algorithm
	 * What happens if we don’t want the time complexity to be dependent on N, and the collection is read only?
	 * time complexity : O(m), O(m log m) in worst case
	 * */
	public static <T> Set<T> randomSample4(List<T> items, int m) {
		HashSet<T> res = new HashSet<T>();
		int n = items.size();
		for (int i = n - m; i < n; i++) {
			int pos = rd.nextInt(i + 1);
			if (!res.contains(items.get(pos))) {
				res.add(items.get(pos));
			}
			else {
				res.add(items.get(i));
			}
		}
		return res;
	}
	
	/*case5: stream
	 * Sometimes we don’t know the collection size in advance. 
	 * We can only iterate through it, as if it was a data stream with unknown size. 
	 * Reservior sampling
	 * time complexity: O(n): n is the size of stream
	 * */
	public static <T> List<T> randomSample5(List<T> items, int m) {
		List<T> res = new ArrayList<T>();
		int i = 1;
		for (i = 1; i <= m; i++) {
			res.add(items.get(i));
		}
		for (; i < items.size(); i++) {
			int pos = rd.nextInt(i);
			if (pos < m) {
				res.set(pos, items.get(i));
			}
		}
		return res;
		
	}
	
}
/*A note about random number generators

The algorithms above assume that the random number generator has a pure random behavior, but this is rarely the case. The class java.util.Random uses a very popular pseudo number generation approach, called Linear Congruential Generator.  Due to the fact that every generated number is determined by the previously generated one (or initially the seed), there is a bounded number of sequences that can be produced. Java’s Random class uses a state record of 48 bits, so we can never exceed 2^{48} different sequences. This has a huge impact when generating random constructs from a large combinatorial space, such as in the case of subsets or permutations. For example, if we are interested in generating a random subset of size 20 from a set of size 60, we have {60 \choose 20} options, which exceeds 2^{48}. Therefore, the results of any algorithm that uses java.lang.Random would be completely biased because there are many possible subsets that will never be returned. Actually, we will cover only 7% of the subsets space! For many applications this behavior is good enough. For others, which require more accuracy, we should consider a different random source than java.util.Random. After searching the web a little, I found the RngPack library, which seems to have some powerful random number generator implementations.
 * */
