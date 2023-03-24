package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>
{
	private BinaryNode<Type> root;
	private boolean operated;
	private boolean operatedAll;
	/**
	 * Default Constructor
	 */
	BinarySearchTree()
	{
		root = null;
	}
	/**
	 * Constructor with initial value
	 * @param value	value to start with
	 */
	BinarySearchTree(Type value)
	{
		root = new BinaryNode<Type>(value);
	}

	@Override
	public boolean add(Type item)
	{
		//assume this call will change the tree
		operated = true;
		if(item == null)	//if the given item is null, nothing will change so return false
			return false;
		//call the recursive method and set root equal to it
		root = addRec(root, item);
		return operated;	//did it change anything
	}
	/**
	 * Recursive method for adding a node to the tree
	 * @param root	root of the tree to add to
	 * @param item	value of the node to add
	 * @return	the root node with the added node
	 */
	private BinaryNode<Type> addRec(BinaryNode<Type> root, Type item)
	{
		if(root == null)	//base case
		{
			root = new BinaryNode<Type>(item);
			return root;
		}
		else if(item.compareTo(root.getData()) < 0)	//if it's less, move left
			root.setLeftChild(addRec(root.getLeftChild(), item));	//recurse
		else if(item.compareTo(root.getData()) > 0)	//if it's more, move right
			root.setRightChild(addRec(root.getRightChild(), item));	//recurse

		if(root.getData() == item)	//check if nothing changed
			operated = false;	//if nothing changed, prepare to return false in add()
		return root;	//return new root value
	}
	@Override
	public boolean addAll(Collection<? extends Type> items)
	{
		operatedAll = false;
		var iter = items.iterator();
		//if there's nothing in the collection, nothing should change
		if(iter.hasNext() == false)
			return false;
		//for each item in the collection, add it
		iter.forEachRemaining((item) -> {
			if(add(item))
				operatedAll = true;
		});
		return operatedAll;
	}

	@Override
	public void clear()
	{
		root = null;
	}

	@Override
	public boolean contains(Type item)
	{
		if(root == null)
			return false;
		return containsRec(root, item);
	}
	/**
	 * Recursive method to determine if the tree contains an item
	 * @param root	starting point
	 * @param item	item to test if present
	 * @return	true if item is present, false if it is not
	 */
	private boolean containsRec(BinaryNode<Type> root, Type item) 
	{
	    int comp = item.compareTo(root.getData());
	    if(comp == 0) 
	    	return true;
	    if(comp < 0 && root.getLeftChild() != null && containsRec(root.getLeftChild(), item)) 
	    	return true;
	    if(comp > 0 && root.getRightChild() != null && containsRec(root.getRightChild(), item)) 
	    	return true;
	    // no matching node was found
	    return false;
	}

	@Override
	public boolean containsAll(Collection<? extends Type> items)
	{
		operated = true;
		var iter = items.iterator();
		if(iter.hasNext() == false)
			return false;
		iter.forEachRemaining((item) -> {
			if(operated && !contains(item))
				operated = false;
		});
		return operated;
	}

	@Override
	public Type first() throws NoSuchElementException
	{
		if(root == null)
			throw new NoSuchElementException("Set is empty");
		return root.getLeftmostNode().getData();
	}

	@Override
	public boolean isEmpty()
	{
		return root == null;
	}

	@Override
	public Type last() throws NoSuchElementException
	{
		if(root == null)
			throw new NoSuchElementException("Set is empty");
		return root.getRightmostNode().getData();
	}

	@Override
	public boolean remove(Type item)
	{
		operated = false;
		if(item == null)
			return false;
		root = removeRec(root, item);
		return operated;
	}
	/**
	 * Recursive method for removing an item from a tree
	 * @param root
	 * @param item
	 * @return	updated root
	 */
	private BinaryNode<Type> removeRec(BinaryNode<Type> root, Type item)
	{
		if(root == null)	//base case
			return root;
		int comp = item.compareTo(root.getData());	//compare item to current node
		if(comp < 0)	//if It's less, move left
			root.setLeftChild(removeRec(root.getLeftChild(), item));
		else if(comp > 0)	//if It's greater, move right
			root.setRightChild(removeRec(root.getRightChild(), item));
		else
		{	//found the item
			operated = true;	//since we will now change the tree, prepare to return true in remove()
			if(root.getLeftChild() == null && root.getRightChild() == null)	//if It's children are both null,
				root = null;	//just remove it by setting it to null
			else if(root.getRightChild() != null)	//if it has a right child, find the left-most value from the right child to replace it
			{
				root.setData(successor(root));	//set the data as the replacement's data
				root.setRightChild(removeRec(root.getRightChild(), root.getData()));	//remove the replacement node so there's no duplicate data
			}
			else	//if it has a left child, find the right-most value from the left child to replace it
			{
				root.setData(predecessor(root));	//set the data as the replacement's data
				root.setLeftChild(removeRec(root.getLeftChild(),root.getData()));	//remove the replacement node so there's no duplicate data
			}
		}
		return root;
	}
	/**
	 * Finds and Returns a left-side replacement
	 * @param root
	 * @return
	 */
	private Type predecessor(BinaryNode<Type> root)
	{
		root = root.getLeftChild();
		while(root.getRightChild() != null)
			root = root.getRightChild();
		return root.getData();
	}
	/**
	 * Finds and returns a right-side replacement
	 * @param root
	 * @return
	 */
	private Type successor(BinaryNode<Type> root)
	{
		root = root.getRightChild();
		
		while(root.getLeftChild() != null)
			root = root.getLeftChild();
		
		return root.getData();
	}
	@Override
	public boolean removeAll(Collection<? extends Type> items)
	{
		operatedAll = false;
		var iter = items.iterator();
		if(iter.hasNext() == false)
			return false;
		iter.forEachRemaining((item) -> {
			if(remove(item))
				operatedAll = true;
		});
		return operatedAll;
	}

	@Override
	public int size()
	{
		if(root == null)
			return 0;
		return sizeRec(root);
	}
	/**
	 * Recursive method for finding the size of a tree
	 * @param root
	 * @param toReturn
	 * @return	the size
	 */
	private int sizeRec(BinaryNode<Type> root)
	{
		if(root == null)
			return 0;

		return sizeRec(root.getLeftChild())+ 1 + sizeRec(root.getRightChild());
	}
	@Override
	public ArrayList<Type> toArrayList()
	{
		var arr = new ArrayList<Type>();
		return toArrayListRec(root,arr);
	}
	/**
	 * Recursive method for returning a sorted array list
	 * containing all of the items in a tree
	 * @param root
	 * @param arr
	 * @return
	 */
	private ArrayList<Type> toArrayListRec(BinaryNode<Type> root, ArrayList<Type> arr)
	{
		if(root == null)
			return arr;
		toArrayListRec(root.getLeftChild(), arr);	//move left
		arr.add(root.getData());	//before moving right, add your left value
		toArrayListRec(root.getRightChild(), arr);	//move right
		return arr;
	}
	/**
	 * Method to visualize the tree
	 * driver for the recursive visualize method
	 */
	public void toDot()
	{
		if(root == null)
			System.out.println("empty tree");
		toDotRec(root);
	}
	/**
	 * Recursive method to visualize a tree
	 * prints out the graph in a simple format
	 * @param root
	 */
	private void toDotRec(BinaryNode<Type> root)
	{
		if(root == null)
			return;
		if(root.getLeftChild() != null)
		{
			System.out.print(root.getData().toString() + "--" + root.getLeftChild().getData().toString() + "\n");
			toDotRec(root.getLeftChild());
		}
		if(root.getRightChild() != null)
		{
			System.out.print(root.getData().toString() + "--" + root.getRightChild().getData().toString() + "\n");
			toDotRec(root.getRightChild());
		}
	}

}
