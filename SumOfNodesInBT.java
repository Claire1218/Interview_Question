package Phone_Screen;

import java.util.ArrayList;
import java.util.List;

public class SumOfNodesInBT {

	// case1: get all path in the binary tree where the sum of all nodes of the
	// given path is equal to target.
	public List<List<Integer>> pathSum(TreeNode root, int sum) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		if (root == null)
			return res;
		List<Integer> curPath = new ArrayList<Integer>();
		curPath.add(root.val);
		pathHelper(root, sum - root.val, curPath, res);
		return res;
	}

	private void pathHelper(TreeNode node, int sum, List<Integer> curPath,
			List<List<Integer>> res) {
		if (node == null)
			return;
		if (node.left == null && node.right == null && sum == 0) {
			res.add(new ArrayList<Integer>(curPath));
			return;
		}
		if (node.left != null) {
			curPath.add(node.left.val);
			pathHelper(node.left, sum - node.left.val, curPath, res);
			curPath.remove(curPath.size() - 1);
		}
		if (node.right != null) {
			curPath.add(node.right.val);
			pathHelper(node.right, sum - node.right.val, curPath, res);
			curPath.remove(curPath.size() - 1);
		}
	}

	// case2: sum root to leaf
	/*
	 * Given a binary tree containing digits from 0-9 only, each root-to-leaf
	 * path could represent a number. An example is the root-to-leaf path
	 * 1->2->3 which represents the number 123. Find the total sum of all
	 * root-to-leaf numbers.
	 */
	public int sumNumbers(TreeNode root) {
		if (root == null)
			return 0;
		return sumNumberHelper(root, 0);
	}

	private int sumNumberHelper(TreeNode node, int curSum) {
		if (node == null)
			return 0;
		if (node.left == null && node.right == null) {
			curSum = curSum * 10 + node.val;
			return curSum;
		}
		return sumNumberHelper(node.left, curSum * 10 + node.val)
				+ sumNumberHelper(node.right, curSum * 10 + node.val);
	}

	// case3: maximum path sum in binary tree
	// time complexity O(n);
	// space complexity O(log n)
	public int maxPathSum(TreeNode root) {
		if (root == null)
			return 0;
		maxHelper(root);
		return max;
	}

	int max = Integer.MIN_VALUE;

	private int maxHelper(TreeNode node) {
		if (node == null)
			return 0;
		int left = maxHelper(node.left);
		int right = maxHelper(node.right);
		int localPath = Math.max(left, 0) + Math.max(right, 0) + node.val;
		max = Math.max(max, localPath);
		return Math.max(Math.max(left, right), 0) + node.val;
	}

	private class TreeNode {
		int val;
		TreeNode left, right;

		public TreeNode(int val) {
			this.val = val;
			left = right = null;
		}
	}

}
