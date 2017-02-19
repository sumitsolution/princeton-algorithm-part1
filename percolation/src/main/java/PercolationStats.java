import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * 
 */

/**
 * Provides service to find percolation statistics.
 * 
 * @author Sumit Kumar
 *
 */
public class PercolationStats {

	private static final int ONE = 1;
	private double[] percolationThresholds;
	private int trails;

	/**
	 * Constructor.
	 * 
	 * @param n
	 *            : Site linear or vertical dimension.
	 * @param trials
	 *            : No of experiment trails.
	 * @throws IllegalArgumentException
	 *             : In case of Invalid values.
	 */
	public PercolationStats(int n, int trials) {

		validate(n, "site dimension");
		validate(trials, "trails");

		this.trails = trials;
		percolationThresholds = new double[trails];
		executeTrails(n);

	}

	/**
	 * 
	 * @return mean of percolation probability.
	 */
	public double mean() {
		return StdStats.mean(percolationThresholds);

	}

	/**
	 * 
	 * @return standard deviation of percolation probability.
	 */
	public double stddev() {
		return StdStats.stddev(percolationThresholds);

	}

	/**
	 * 
	 * @return confidence low of percolation probability
	 */
	public double confidenceLo() {
		return mean() - (1.96 * stddev() / Math.sqrt(trails));

	}

	/**
	 * 
	 * @return confidence high of of percolation probability
	 */
	public double confidenceHi() {
		return mean() + (1.96 * stddev() / Math.sqrt(trails));

	}

	private void executeTrails(int n) {
		int numberOfOpenSite;
		int numberOfSites = n * n;
		int rowVal = 0;
		int colVal = 0;

		for (int i = 0; i < trails; i++) {
			Percolation p = new Percolation(n);
			numberOfOpenSite = 0;
			while (!p.percolates()) {

				rowVal = StdRandom.uniform(ONE, n + ONE);
				colVal = StdRandom.uniform(ONE, n + ONE);
				if (!p.isOpen(rowVal, colVal)) {
					p.open(rowVal, colVal);
					numberOfOpenSite++;
				}

			}
			percolationThresholds[i] = (double) numberOfOpenSite / numberOfSites;
		}
	}

	private void validate(int value, String type) {
		if (value < ONE) {
			throwIllegalArgumentException("Invalid value " + value + ". The value of " + type + " must be  >= " + ONE);
		}

	}

	private void throwIllegalArgumentException(String message) {
		throw new IllegalArgumentException(message);

	}

	public static void main(String[] args) {

		PercolationStats pStats = new PercolationStats(2, 10000);
		System.out.println("Mean :" + pStats.mean());
		System.out.println("DEV :" + pStats.stddev());
		System.out.println("LOW :" + pStats.confidenceLo());
		System.out.println("HIGH :" + pStats.confidenceHi());

	}

}
