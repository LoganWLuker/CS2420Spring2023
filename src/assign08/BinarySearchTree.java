package assign08;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

public class BinarySearchTree<Type extends Comparable<? super Type>> implements SortedSet<Type>
{
	private BinaryNode<Type> root;
	private boolean operated = true;
	private boolean removed = false;
	
	BinarySearchTree()
	{
		root = null;
	}
	BinarySearchTree(Type value)
	{
		root = new BinaryNode<Type>(value);
	}

	@Override
	public boolean add(Type item)
	{
		operated = true;
		if(item == null)
			operated = false;
		root = addRec(root, item);
		return operated;
	}
	/**
	 * Recursive method for adding a node to the tree
	 * @param root	root of the tree to add to
	 * @param item	value of the node to add
	 * @return	the root node with the added node
	 */
	private BinaryNode<Type> addRec(BinaryNode<Type> root, Type item)
	{
		if(root == null)
		{
			root = new BinaryNode<Type>(item);
			return root;
		}
		else if(item.compareTo(root.getData()) < 0)
			root.setLeftChild(addRec(root.getLeftChild(), item));
		else if(item.compareTo(root.getData()) > 0)
			root.setRightChild(addRec(root.getRightChild(), item));
		//return unchanged root
		if(root.getData() == item)
			operated = false;
		return root;
	}
	@Override
	public boolean addAll(Collection<? extends Type> items)
	{
		var iter = items.iterator();
		if(iter.hasNext() == false)
			return false;
		iter.forEachRemaining((item) -> {
			add(item);
		});
		return operated;
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

	private BinaryNode<Type> removeRec(BinaryNode<Type> root, Type item)
	{
		if(root == null)
			return root;
		int comp = item.compareTo(root.getData());
		if(comp < 0)
			root.setLeftChild(removeRec(root.getLeftChild(), item));
		else if(comp > 0)
			root.setRightChild(removeRec(root.getRightChild(), item));
		else
		{
			operated = true;
			if(root.getLeftChild() == null && root.getRightChild() == null)
				root = null;
			else if(root.getRightChild() != null)
			{
				root.setData(successor(root));
				root.setRightChild(removeRec(root.getRightChild(), root.getData()));
			}
			else
			{
				root.setData(predecessor(root));
				root.setLeftChild(removeRec(root.getLeftChild(),root.getData()));
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
		removed = false;
		var iter = items.iterator();
		if(iter.hasNext() == false)
			return false;
		iter.forEachRemaining((item) -> {
			if(remove(item))
				removed = true;
		});
		return removed;
	}

	@Override
	public int size()
	{
		
		// TODO Auto-generated method stub
		return root.height();
	}

	@Override
	public ArrayList<Type> toArrayList()
	{
		// TODO Auto-generated method stub
		return null;
	}
	public void visualize()
	{
		if(root == null)
			System.out.println("empty tree");
		visualizeRec(root);
	}
	private void visualizeRec(BinaryNode<Type> root)
	{
		if(root == null)
			return;
		if(root.getLeftChild() != null)
		{
			System.out.print(root.getData().toString() + "--" + root.getLeftChild().getData().toString() + "\n");
			visualizeRec(root.getLeftChild());
		}
		if(root.getRightChild() != null)
		{
			System.out.print(root.getData().toString() + "--" + root.getRightChild().getData().toString() + "\n");
			visualizeRec(root.getRightChild());
		}
	}

}
