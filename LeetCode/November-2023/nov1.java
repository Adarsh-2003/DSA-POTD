/*
501. Find Mode in Binary Search Tree

Given the root of a binary search tree (BST) with duplicates, return all the mode(s) (i.e., the most frequently occurred element) in it.

If the tree has more than one mode, return them in any order.

Assume a BST is defined as follows:

The left subtree of a node contains only nodes with keys less than or equal to the node's key.
The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
Both the left and right subtrees must also be binary search trees.
 

Example 1:


Input: root = [1,null,2,2]
Output: [2]
Example 2:

Input: root = [0]
Output: [0]
 

Constraints:

The number of nodes in the tree is in the range [1, 104].
-105 <= Node.val <= 105
*/

class Solution {
  public int[] findMode(TreeNode root) {
    List<Integer> ans = new ArrayList<>();
    // count[0] := currCount
    // count[1] := maxCount
    int[] count = new int[2];

    inorder(root, count, ans);
    return ans.stream().mapToInt(Integer::intValue).toArray();
  }

  private TreeNode pred = null;

  private void inorder(TreeNode root, int[] count, List<Integer> ans) {
    if (root == null)
      return;

    inorder(root.left, count, ans);
    updateCount(root, count, ans);
    inorder(root.right, count, ans);
  }

  private void updateCount(TreeNode root, int[] count, List<Integer> ans) {
    if (pred != null && pred.val == root.val)
      ++count[0];
    else
      count[0] = 1;

    if (count[0] > count[1]) {
      count[1] = count[0];
      ans.clear();
      ans.add(root.val);
    } else if (count[0] == count[1]) {
      ans.add(root.val);
    }

    pred = root;
  }
}
