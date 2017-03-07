import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * @author Sumit Kumar
 *
 */
public class Permutation {

	public static void main(String[] args) {

		if (isValidNumberOfArguments(args)) {
			int k = Integer.parseInt(args[0]);
			RandomizedQueue<String> queue = new RandomizedQueue<>();
			readInputValues(queue);
			generatePermutations(queue, k);
		}
	}

	private static void generatePermutations(RandomizedQueue<String> queue, int k) {

		for (int i = 0; i < k; i++) {
			StdOut.println(queue.dequeue());
		}

	}

	private static void readInputValues(RandomizedQueue<String> queue) {
		String currentVal = null;

		while (!StdIn.isEmpty()) {
			currentVal = StdIn.readString();
			if (!isNull(currentVal)) {
				queue.enqueue(currentVal);
			}

		}
	}

	private static boolean isNull(Object value) {
		return value == null;
	}

	private static boolean isValidNumberOfArguments(String[] args) {
		return args.length > 0;
	}

}
