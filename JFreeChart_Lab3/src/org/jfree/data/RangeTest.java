package org.jfree.data;

import org.junit.*;
import static org.junit.Assert.*;
import org.jfree.data.Range;

public class RangeTest {
	
	private Range exampleRange;
	private Range positiveBounds;
	private Range negativeBounds;
	private Range equalBounds;
	private Range exampleRange2;
	private Range exampleRange4;
	private Range nullRange;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
    }
	
	@Before
    public void setUp() throws Exception { 
		exampleRange = new Range(-1, 1);
		exampleRange2 = new Range(-1, 1);
		exampleRange4 = new Range(1, -1);
		positiveBounds = new Range(2, 5);
		negativeBounds = new Range(-4, -2);
		equalBounds = new Range(2, 2);
    }
	
	
	// testing method: contains()
	@Test
	public void testContainsPositiveReturnsTrue() {
		assertTrue(exampleRange.contains(0.5));
	}
	
	@Test
	public void testContainsPositiveReturnsFalse() {
		assertFalse(exampleRange.contains(1.5));
	}
	
	@Test
	public void testContainsNegitiveReturnsTrue() {
		assertTrue(exampleRange.contains(-0.5));
	}
	
	@Test
	public void testContainsNegitiveReturnsFalse() {
		assertFalse(exampleRange.contains(-1.5));
	}
	
	@Test
	public void testContainsLowerBoundReturnsTrue() {
		assertTrue(exampleRange.contains(-1));
	}
	
	@Test
	public void testContainsUpperBoundReturnsTrue() {
		assertTrue(exampleRange.contains(1));
	}
	
	
	
	// testing method: getLength()
	@Test
	public void testGetLength() {
		assertEquals("Length from -1 to 1 should be 2", 
				2, exampleRange.getLength(), .000000001d);
	}
	
	
	@Test
	public void testGetLength2() {
		assertEquals("Length from -1 to 1 should be 2", 
				2, exampleRange4.getLength(), .000000001d);
	}
	
	
	@Test
	public void testGetLengthPositiveBounds() {
		assertEquals("Length from 2 to 5 should be 3", 
				3, positiveBounds.getLength(), .000000001d);
	}
	
	@Test
	public void testGetLengthNegitiveBounds() {
		assertEquals("Length from -4 to -2 should be 2", 
				2, negativeBounds.getLength(), .000000001d);
	}
	
	
	@Test
	public void testGetLengthEqualBounds() {
		assertEquals("Length from 2 to 2 should be 0", 
				0, equalBounds.getLength(), .000000001d);
	}
	
	
	
	// testing method: getLowerBound()
	@Test
	public void testGetLowerBound() {
		assertEquals("Lower bound of -1 to 1 is -1",
				-1, exampleRange.getLowerBound(), .000000001d);
	}
	
	@Test
	public void testGetLowerBoundEqualBounds() {
		assertEquals("Lower bound of 2 to 2 is 2",
				2, equalBounds.getLowerBound(), .000000001d);
	}
	
	
	
	// testing method: getUpperBound()
	@Test
	public void testGetUpperBoundEqualBounds() {
		assertEquals("Upper bound of 2 to 2 is 2",
				2, equalBounds.getUpperBound(), .000000001d);
	}
	
	// FAILS
	@Test
	public void testGetUpperBound() {
		assertEquals("Upper bound of -1 to 1 is 1",
				1, exampleRange.getUpperBound(), .000000001d);
	}
	
	
	
	// testing method: equals()
	@Test
	public void testEqualsSameRangeReturnsTrue() {
		assertTrue(exampleRange.equals(exampleRange2));
	}
	
	@Test
	public void testEqualsDifferentRangeReturnsFalse() {
		assertFalse(exampleRange.equals(positiveBounds));
	}
	
	@Test
	public void testEqualsNullObjectReturnsFalse() {
		assertFalse(exampleRange.equals(nullRange));
	}
	
	@Test
	public void testEqualsSameRangeDifferentBoundsReturnsFalse() {
		assertFalse(exampleRange.equals(negativeBounds));
	}
	

	
	
	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
