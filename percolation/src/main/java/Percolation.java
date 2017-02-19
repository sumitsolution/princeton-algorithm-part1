
/**
 * 
 */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * <code>Percolation</code> provide service to check if system percolates or
 * not.
 * 
 * @author Sumit Kumar
 *
 */
public class Percolation {

	private static final short OFFSET_ONE = 1;

	private boolean[][] sitesOccupancyStatus;

	private int siteSize;

	private int numberOfSites;

	private int noOfOpenSite;

	private WeightedQuickUnionUF weightedQuickUnionUF;

	/**
	 * Constructor .
	 * 
	 * @param n
	 *            : Site linear or vertical dimension.
	 * 
	 * @throws IllegalArgumentException
	 *             : In case of Invalid values.
	 */
	public Percolation(int n) {
		validateSiteDimension(n);
		siteSize = n;
		numberOfSites = n * n;
		sitesOccupancyStatus = new boolean[siteSize][siteSize];
		weightedQuickUnionUF = new WeightedQuickUnionUF(numberOfSites + 2);
	}

	/**
	 * Opens the site at given row and index.
	 * 
	 * @param row
	 *            :row index.
	 * @param col
	 *            : column index.
	 * @throws IndexOutOfBoundsException
	 *             : In case of Invalid values.
	 */
	public void open(int row, int col) {
		validateRowAndColumn(row, col);
		if (!getSiteStatus(row, col)) {
			sitesOccupancyStatus[row - OFFSET_ONE][col - OFFSET_ONE] = true;
			noOfOpenSite++;
			int siteIndex = computeQuickUnionIndex(row, col);
			computeUnionWithVirtualSite(siteIndex);
			computeUnionWithAdjacentSite(row, col, siteIndex);
		}
	}

	/**
	 * Checks is site is opened or not.
	 * 
	 * @param row
	 *            : row index.
	 * @param col
	 *            : column index
	 * @return
	 *         <p>
	 *         true : If site is opened.
	 *         </p>
	 *         <p>
	 *         false : If site is not opened.
	 *         </p>
	 * 
	 * @throws IndexOutOfBoundsException
	 *             : In case of Invalid values.
	 */
	public boolean isOpen(int row, int col) {
		validateRowAndColumn(row, col);
		return getSiteStatus(row, col);

	}

	/**
	 * 
	 * Checks is site is full or not.
	 * 
	 * @param row
	 *            : row index.
	 * @param col
	 *            : column index
	 * @return
	 *         <p>
	 *         true : If site is full.
	 *         </p>
	 *         <p>
	 *         false : If site is not full.
	 *         </p>
	 * @throws IndexOutOfBoundsException
	 *             : In case of Invalid values.
	 */
	public boolean isFull(int row, int col) {
		validateRowAndColumn(row, col);
		return getSiteStatus(row, col)
				&& weightedQuickUnionUF.connected(numberOfSites, computeQuickUnionIndex(row, col));

	}

	/**
	 * Gets the value of number of open site.
	 * 
	 * @return : Number of open site.
	 */
	public int numberOfOpenSites() {
		return noOfOpenSite;

	}

	/**
	 * Checks if the system percolates or not.
	 * 
	 * @return
	 *         <p>
	 *         true : If system percolates.
	 *         </p>
	 *         <p>
	 *         false : If system doesn't percolates.
	 *         </p>
	 * 
	 */
	public boolean percolates() {
		return weightedQuickUnionUF.connected(numberOfSites, numberOfSites + OFFSET_ONE);

	}

	private void validateSiteDimension(int n) {
		if (isLesserThanLowerBound(1, n)) {
			throwIllegalArgumentException(
					"Invalid value  " + n + " .The value of site dimension  must be greater than 0 ");
		}

	}

	private void throwIllegalArgumentException(String value) {

		throw new IllegalArgumentException(value);

	}

	private boolean isLesserThanLowerBound(int lowerBound, int currentValue) {

		return currentValue < lowerBound;
	}

	private void validateRowAndColumn(int row, int col) {
		if (!isInRange(row)) {
			throwIndexOutOfBoundsException(
					"Invalid row value :" + row + ". The value must be in range of 1 <=row<=" + siteSize);
		}

		if (!isInRange(col)) {
			throwIndexOutOfBoundsException(
					"Invalid row value :" + col + ". The value must be in range of 1 <=col<=" + siteSize);
		}

	}

	private void throwIndexOutOfBoundsException(String value) {
		throw new IndexOutOfBoundsException(value);

	}

	private boolean isInRange(int value) {
		return value >= 1 && value <= siteSize;
	}

	private boolean getSiteStatus(int row, int col) {
		return sitesOccupancyStatus[row - OFFSET_ONE][col - OFFSET_ONE];
	}

	private int computeQuickUnionIndex(int row, int col) {
		return ((row - OFFSET_ONE) * siteSize) + col - OFFSET_ONE;
	}

	private void computeUnionWithAdjacentSite(int row, int col, int siteIndex) {
		if (isRightSitePresentAndOpened(row, col)) {
			weightedQuickUnionUF.union(siteIndex, computeQuickUnionIndex(row, col + OFFSET_ONE));
		}

		if (isLeftSitePresentAndOpened(row, col)) {
			weightedQuickUnionUF.union(siteIndex, computeQuickUnionIndex(row, col - OFFSET_ONE));
		}

		if (isUpperSitePresentAndOpened(row, col)) {
			weightedQuickUnionUF.union(siteIndex, computeQuickUnionIndex(row + OFFSET_ONE, col));
		}

		if (isLowerSitePresentAndOpened(row, col)) {
			weightedQuickUnionUF.union(siteIndex, computeQuickUnionIndex(row - OFFSET_ONE, col));

		}
	}

	private boolean isLowerSitePresentAndOpened(int row, int col) {
		return row - OFFSET_ONE >= OFFSET_ONE && getSiteStatus(row - OFFSET_ONE, col);
	}

	private boolean isUpperSitePresentAndOpened(int row, int col) {
		return row + OFFSET_ONE <= siteSize && getSiteStatus(row + OFFSET_ONE, col);
	}

	private boolean isLeftSitePresentAndOpened(int row, int col) {
		return col - OFFSET_ONE >= OFFSET_ONE && getSiteStatus(row, col - OFFSET_ONE);
	}

	private boolean isRightSitePresentAndOpened(int row, int col) {
		return col + OFFSET_ONE <= siteSize && getSiteStatus(row, col + OFFSET_ONE);
	}

	private void computeUnionWithVirtualSite(int siteIndex) {
		if (isTopSite(siteIndex)) {
			weightedQuickUnionUF.union(siteIndex, numberOfSites);
		}

		if (isBottomSite(siteIndex)) {
			weightedQuickUnionUF.union(siteIndex, numberOfSites + OFFSET_ONE);
		}
	}

	private boolean isBottomSite(int siteIndex) {
		return siteIndex >= numberOfSites - siteSize;
	}

	private boolean isTopSite(int siteIndex) {
		return siteIndex < siteSize;
	}

	public static void main(String[] args) {

		Percolation p = new Percolation(2);

		System.out.println(p.percolates());
		p.open(1, 1);
		System.out.println("No of open sites :" + p.numberOfOpenSites());
		p.open(2, 2);
		p.open(2, 2);
		p.open(2, 2);
		p.open(2, 1);
		System.out.println("No of open sites :" + p.numberOfOpenSites());
		System.out.println(p.percolates());

	}
}
