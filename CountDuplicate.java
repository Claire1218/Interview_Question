package Phone_Screen;

import java.util.HashMap;

public class FindDuplicatesInBinaryTree {
	
	class TreeNode {
		TreeNode left, right;
		int val;
		public TreeNode (int val) {
			this.val = val;
		}
	}
	//case 1: find the duplicates for the given value
	//time complexity: O(n)
	//space complexity: O(1)
	public boolean findDuplicate(TreeNode root, int val) {
		if (root ==  null)
			return false;
		if (root.val == val)
			return true;
		return findDuplicate(root.left, val) || findDuplicate(root.right, val);
	}
	//case2: count the number of duplicates in the unsorted binary tree
	//time complexity: O(n);
	//space complexity: O(n) at worst case;
	public int countDuplicates(TreeNode root) {
		if (root == null)
			return 0;
		int count = 0;
		HashMap<Integer, Integer> visited = new HashMap<Integer, Integer>();
		countHelper(root, visited);
		for (Integer key: visited.keySet()) {
			if (visited.get(key) > 1)
				count++;
		}
		return count;
	}
	private void countHelper(TreeNode node, HashMap<Integer, Integer> visited) {
		if (node == null)
			return;
		countHelper(node.left, visited);
		if (visited.containsKey(node.val)) {
			visited.put(node.val, visited.get(node.val) + 1);
		}
		else {
			visited.put(node.val, 1);
		}
		countHelper(node.right, visited);		
	}
	//case3: what if the sorted binary tree
	//time complexity： O(n)
	//space complexity: O(logn) --> recursive stack
	public int countDuplicates1(TreeNode root) {
		if (root == null)
			return 0;
		countHelper1(root);
		return totalCount;
	}
	private int DupCount = 0;
	private int totalCount = 0;
	private TreeNode pre = null;
	public void countHelper1(TreeNode node) {
		if (node == null)
			return;
		countHelper1(node.left);
		if (pre != null) {
			if (pre.val == node.val)
				DupCount++;
			else {
				if (DupCount > 0)
					totalCount++;
				DupCount = 0;
			}
		}
		pre = node;
		countHelper1(node.right);
	}
	

}
