import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * 
 */

/**
 * @author Sumit Kumar
 *
 */
public class PercolationTest {

	private Percolation percolation;

	@Before
	public void setUp() {
		percolation = new Percolation(2);

	}

	@Test
	public void testOpenShouldBeTrueIfSiteIsOpened() {
		percolation.open(1, 1);
		assertTrue("The site must be opened", percolation.isOpen(1, 1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testOpenShouldThrowExceptionIfValueIsNotInRange() {
		percolation.open(5, 6);

	}

	@Test
	public void testIsFullShouldBeFalseIfSiteIsNotOpened() {
		assertFalse("The site must not be full", percolation.isFull(1, 1));

	}

	/**
	 * Test method for {@link Percolation#numberOfOpenSites()}.
	 */
	@Test
	public void testNumberOfOpenSites() {
		percolation.open(1, 1);
		percolation.open(2, 2);
		percolation.open(2, 2);
		percolation.open(2, 2);
		assertEquals("Number  of open site must be 2", 2, percolation.numberOfOpenSites());

	}

	/**
	 * Test method for {@link Percolation#percolates()}.
	 */
	@Test
	public void testPercolates() {
		percolation.open(1, 1);
		percolation.open(2, 2);
		percolation.open(2, 1);
		assertTrue("System must percolate. ", percolation.percolates());
	}

}
