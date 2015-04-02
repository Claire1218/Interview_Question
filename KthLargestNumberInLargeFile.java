package Phone_Screen;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;

public class KthLargestNumberInLargeFile {
	
	public int kthLargestNumber(File file, int k) {
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(k); // we can implement this data structure by ourself
		try {
			@SuppressWarnings("resource")
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;
			while ((line = br.readLine()) != null) {
				int curNum = Integer.parseInt(line);
				if (pq.size() < k) { // less than k items, add into minHeap directly
					pq.add(curNum);
				}
				else {
					int min = pq.peek();
					if (curNum > min) { //replace the larger one and sort it again;
						pq.poll();
						pq.add(curNum);
					}
				}
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		if (pq.isEmpty())
			throw new NoSuchElementException(); 
		return pq.poll();
	}
	

}
