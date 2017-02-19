import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Sumit Kumar
 *
 */
public class PercolationStatsTest {

	private PercolationStats percolationStats;

	@Before
	public void setUp() {
		percolationStats = new PercolationStats(20, 1000);

	}

	@Test
	public void testPercolationStats() {

		assertEquals("Mean must be equal", .58, percolationStats.mean(), .02);
		assertEquals("Std dev must be equal", .03, percolationStats.stddev(), .02);
		assertEquals("Confidence hi must be equal", 0.60, percolationStats.confidenceHi(), .02);
		assertEquals("Confidence lo must be equal", 0.57, percolationStats.confidenceLo(), .02);

	}

}
