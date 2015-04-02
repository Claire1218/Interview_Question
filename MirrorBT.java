package Phone_Screen;

public class MirrorBinaryTree {
	
	public TreeNode mirrorBT(TreeNode root) {
		if (root == null)
			return null;
		TreeNode newRoot = mirrorHelper(root);
		return newRoot;
	}
	private TreeNode mirrorHelper(TreeNode node) {
		if (node == null)
			return null;
		TreeNode newNode = new TreeNode(node.val);
		newNode.left = mirrorBT(node.right);
		newNode.right = mirrorBT(node.left);
		return newNode;
	}
	
	public static void main(String[] args) {
		TreeNode node0 = new TreeNode(1);
		TreeNode node2 = new TreeNode(2);
		TreeNode node3 = new TreeNode(3);
		TreeNode node4 = new TreeNode(4);
		TreeNode node5 = new TreeNode(5);
		
		node0.left = node2;
		node0.right = node3;
		node2.left = node4;
		node2.right = node5;
		
		MirrorBinaryTree test = new MirrorBinaryTree();
		test.mirrorBT(node0);
	}
	
	private static class TreeNode {
		int val;
		TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
		}
	}

}
