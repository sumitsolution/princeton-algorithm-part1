import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * @author Sumit Kumar
 *
 */
public class Deque<Item> implements Iterable<Item> {

	private int size;

	private Node<Item> first;

	private Node<Item> last;

	/**
	 * Constructor.
	 */
	public Deque() {

	}

	/**
	 * Checks if queue is empty.
	 * 
	 * @return :
	 *         <p>
	 *         true: If empty.
	 *         </p>
	 *         <p>
	 *         false : Not empty.
	 *         </p>
	 */
	public boolean isEmpty() {
		return size == 0;

	}

	/**
	 * Gets size of the queue.
	 * 
	 * @return size of queue.
	 */
	public int size() {
		return size;
	}

	/***
	 * Adds item to front of the queue.
	 * 
	 * @param item
	 *            : <code>Item</code> to add.
	 */
	public void addFirst(Item item) {

		validateItem(item);

		Node<Item> node = new Node<>();
		node.data = item;

		if (isEmpty()) {
			first = last = node;
		} else {
			node.next = first;
			first.previous = node;
			first = node;
		}

		size++;

	}

	/***
	 * Adds item to last of the queue.
	 * 
	 * @param item
	 *            : <code>Item</code> to add.
	 */
	public void addLast(Item item) {
		validateItem(item);

		Node<Item> node = new Node<>();
		node.data = item;

		if (isEmpty()) {
			first = last = node;
		} else {
			node.previous = last;
			last.next = node;
			last = node;
		}

		size++;

	}

	/**
	 * Removes item from front of the queue.
	 * 
	 * @return <code>Item</code>.
	 */
	public Item removeFirst() {

		validateRemove();

		Item val = first.data;

		if (isSingleNode()) {
			first = last = null;
		} else {
			Node<Item> current = first;
			first = first.next;
			first.previous = null;
			current.next = null;
		}

		size--;

		return val;

	}

	/**
	 * Removes item from last of the queue.
	 * 
	 * @return <code>Item</code>.
	 */
	public Item removeLast() {
		validateRemove();

		Item val = last.data;

		if (isSingleNode()) {
			first = last = null;
		} else {
			Node<Item> current = last;
			last = last.previous;
			last.next = null;
			current.previous = null;
		}

		size--;

		return val;

	}

	/**
	 * Gets item iterator.
	 * 
	 * @return <code>Iterator</code>.
	 */
	public Iterator<Item> iterator() {
		return new DequeueIterator();

	}

	private void validateRemove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	}

	private boolean isSingleNode() {
		return first == last;
	}

	private void validateItem(Item item) {
		if (isNull(item)) {
			throw new NullPointerException();
		}
	}

	private boolean isNull(Object obj) {
		return obj == null;
	}

	private class DequeueIterator implements Iterator<Item> {

		private Node<Item> currentNode = first;

		@Override
		public boolean hasNext() {
			return !isNull(currentNode);
		}

		@Override
		public Item next() {

			if (!hasNext()) {
				throw new java.util.NoSuchElementException();
			}

			Item val = currentNode.data;
			currentNode = currentNode.next;
			return val;
		}

	}

	private static class Node<Item> {
		private Item data;
		private Node<Item> next;
		private Node<Item> previous;

	}

	public static void main(String[] args) {

	}

}