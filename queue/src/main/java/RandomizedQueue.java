import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

/**
 * @author Sumit Kumar
 *
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

	private static final int DEFAULT_SIZE = 16;

	private int capacity = DEFAULT_SIZE;

	private int size;

	private Item[] elements;

	/**
	 * Constructor.
	 */
	public RandomizedQueue() {
		elements = (Item[]) createObjects(DEFAULT_SIZE);
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
	 * Adds item to the queue.
	 * 
	 * @param item
	 *            : <code>Item</code> to add.
	 */
	public void enqueue(Item item) {
		validateItem(item);

		if (isQueueFull()) {
			changeCapacity(2 * capacity);
		}

		elements[size++] = item;

	}

	/**
	 * Removes random item from the queue.
	 * 
	 * @return <code>Item</code>.
	 */
	public Item dequeue() {
		validateRemove();
		int index = StdRandom.uniform(size);
		Item result = elements[index];
		elements[index] = elements[--size];
		elements[size] = null;

		if (isQuaterFull()) {
			changeCapacity(capacity / 2);
		}

		return result;

	}

	/**
	 * Gets random item from the queue.
	 * 
	 * @return <code>Item</code>.
	 */
	public Item sample() {
		validateRemove();
		int index = StdRandom.uniform(size);
		return elements[index];
	}

	/**
	 * Gets item iterator.
	 * 
	 * @return <code>Iterator</code>.
	 */
	public Iterator<Item> iterator() {
		return new RandomIterator();

	}

	private void validateItem(Item item) {
		if (isNull(item)) {
			throw new NullPointerException();
		}
	}

	private boolean isQueueFull() {
		return size >= capacity;
	}

	private void validateRemove() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}
	}

	private boolean isQuaterFull() {
		return size < (capacity / 4);
	}

	private class RandomIterator implements Iterator<Item> {

		private Item[] currentItems;

		private int index = 0;

		private RandomIterator() {
			if (!isEmpty()) {
				currentItems = (Item[]) createObjects(size);
				System.arraycopy(elements, 0, currentItems, 0, size);
				StdRandom.shuffle(currentItems, 0, size);
			}
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Item next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			return currentItems[index++];
		}

	}

	private Object[] createObjects(int currentCapacity) {
		return new Object[currentCapacity];
	}

	private boolean isNull(Object object) {
		return object == null;

	}

	private void changeCapacity(int newCapacity) {

		Item[] newElements = (Item[]) createObjects(newCapacity);
		System.arraycopy(elements, 0, newElements, 0, size);
		elements = newElements;
		capacity = newCapacity;
	}

	public static void main(String[] args) {
	}

}