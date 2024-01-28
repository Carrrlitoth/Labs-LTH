package bst;

import java.util.ArrayList;
import java.util.Comparator;


public class BinarySearchTree<E> {
  BinaryNode<E> root;  // Används också i BSTVisaulizer
  int size;            // Används också i BSTVisaulizer
  private Comparator<E> comparator;

  public static void main(String[] args) {
	BSTVisualizer bst = new BSTVisualizer("BinaryTree", 400, 400);
	BinarySearchTree<Integer> tree = new BinarySearchTree<>();
	tree.add(5);
	tree.add(10);
	tree.add(117);
	tree.add(120);
	tree.add(8);
	tree.add(3);
	tree.add(5);
	tree.add(8);
	tree.add(5);
	tree.printTree();
	tree.rebuild();
	bst.drawTree(tree);

   }
    
	/**
	 * Constructs an empty binary search tree.
	 */
	public BinarySearchTree() {
		comparator = (e1, e2) -> ((Comparable<E>)e1).compareTo(e2);
		root = null;
		size = 0;
	}
	
	/**
	 * Constructs an empty binary search tree, sorted according to the specified comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		root = null;
		size = 0;
		this.comparator = comparator;
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(root, x);
	}

	private boolean add(BinaryNode<E> n, E x) {
		if (n == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		} else if (n.element == x) {
			return false;
		} else if (comparator.compare(x, n.element) > 0) {
			if (n.right != null) {
				return add(n.right, x);
			} else {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			}
		} else if (comparator.compare(x, n.element)  < 0) {
			if (n.left != null) {
				return add(n.left, x);
			} else {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Computes the height of tree.
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}
	private int height(BinaryNode<E> n) {
		if (n == null) {
	        return 0;
	    }else {

		    int leftHeight = height(n.left);
		    int rightHeight = height(n.right);
	
		    if (leftHeight > rightHeight) {
		        return leftHeight + 1;
		    } else {
		        return rightHeight + 1;
		    }
		}
	}
	
	/**
	 * Returns the number of elements in this tree.
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}
	
	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		print(root);
	}
	private void print(BinaryNode<E> n) {
		if(n!=null) {
			print(n.left);
 			System.out.println(n.element);
 			print(n.right);
		}
	}

	/** 
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> temp = new ArrayList<E>();
		toArray(root, temp);
		root = buildTree(temp, 0, temp.size() - 1);
	}
	
	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if( n != null) {
		toArray(n.left, sorted);
		sorted.add(n.element);
		toArray(n.right, sorted);
		}
	}
	
	/*
	 * Builds a complete tree from the elements from position first to 
	 * last in the list sorted.
	 * Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) { 
            return null; 
        } 
		int mid = (first + last) / 2;
		BinaryNode<E> temp = new BinaryNode<E>(sorted.get(mid));
		temp.left = buildTree(sorted,first, mid - 1); 
		temp.right = buildTree(sorted, mid + 1,last); 
		return temp;
	}
	


	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}	
	}
	
}
