package org.jfree.data;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.jmock.*;
import org.jmock.api.ExpectationError;
import org.junit.*;


public class DataUtilitiesTest {
	private double[] data;
	private double[][] data2D;
	private Mockery mockingContext;
	private Values2D values;
	private KeyedValues keyVal;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
    }
	
	@Before
    public void setUp() throws Exception { 
		data = new double[3];
		data[0] = 1.0; data[1] = 2.0; data[2] = 3.0;
		
		data2D = new double[3][2];
		data2D[0][0] = 1.0;data2D[0][1] = 2.0;data2D[1][0] = 3.0;data2D[1][1] = 4.0;data2D[2][0] = 5.0;data2D[2][1] = 6.0;
		
		mockingContext = new Mockery();
    }
	
	
	// testing method: createNumberArray()
	@Test
    public void testCreateNumberArrayValid() {
        Number[] result = DataUtilities.createNumberArray(data);
        Number[] expected = {1.0, 2.0, 3.0};       
        assertArrayEquals(result, expected);
    }
	
	@Test
	public void testCreateNumberArrayLenngth() {
        Number[] result = DataUtilities.createNumberArray(data);
        Number[] expected = {1.0, 2.0, 3.0};       
        assertEquals(result.length, expected.length);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArrayExceptionThrown() {
		DataUtilities.createNumberArray(null);
	}
	
	
	// testing method: createNumberArray2D()
	@Test
    public void testCreateNumberArray2DValid() {
        Number[][] result = DataUtilities.createNumberArray2D(data2D);
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};       
        assertArrayEquals(result, expected);
    }
	
	@Test
    public void testCreateNumberArray2DLength() {
        Number[][] result = DataUtilities.createNumberArray2D(data2D);
        Number[][] expected = {{1.0, 2.0}, {3.0, 4.0}, {5.0, 6.0}};  
        assertEquals(result.length, expected.length);
    }
	
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArray2DExceptionThrown() {
		DataUtilities.createNumberArray2D(null);
	}
	
	
	
	// testing method: calculateColumnTotal()
	@Test
	public void testcalculateColumnTotalForTwoValues() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	        }
	    });
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(result, 10.0, .000000001d);
	}
	
	@Test
	public void testcalculateColumnTotalForSecondCol() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(2));
	            one(values).getValue(0, 0);
	            will(returnValue(7.5));
	            one(values).getValue(1, 0);
	            will(returnValue(2.5));
	            one(values).getValue(0, 1);
	            will(returnValue(8.5));
	            one(values).getValue(1, 1);
	            will(returnValue(3.5));
	        }
	    });
	    double result = DataUtilities.calculateColumnTotal(values, 1);
	    assertEquals(result, 12.0, .000000001d);
	}
	
	@Test
	public void testCalculateColumnTotalForEmptyTable() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(0));
	        }
	    });
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(result, 0.0, .000000001d);
	}
	
	@Test
	public void testCalculateColumnTotalForSingleValue() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(1));
	            one(values).getValue(0, 0);
	            will(returnValue(10.0));
	        }
	    });
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(result, 10.0, .000000001d);
	}

	@Test
	public void testCalculateColumnTotalForNegativeValues() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(4));
	            one(values).getValue(0, 0);
	            will(returnValue(-2.0));
	            one(values).getValue(1, 0);
	            will(returnValue(-3.0));
	            one(values).getValue(2, 0);
	            will(returnValue(-4.0));
	            one(values).getValue(3, 0);
	            will(returnValue(-5.0));
	        }
	    });
	    double result = DataUtilities.calculateColumnTotal(values, 0);
	    assertEquals(result, -14.0, .000000001d);
	}
	
	@Test(expected = ExpectationError.class)
	public void testCalculateColumnTotalForInvalidColumn() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getRowCount();
	            will(returnValue(2));
	        }
	    });
	    // this will throw an exception because the column index is out of bounds
	    DataUtilities.calculateColumnTotal(values, 2);
	}
	
	
	
	// testing method: calculateRowTotal()
	@Test
	public void testcalculateRowTotalForTwoValues() {
	     values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	         }
	     });
	     double result = DataUtilities.calculateRowTotal(values, 0);
	     assertEquals(result, 10.0, .000000001d);
	}
	
	@Test
	public void testcalculateRowTotalForSecondRow() {
	     values = mockingContext.mock(Values2D.class);
	     mockingContext.checking(new Expectations() {
	         {
	             one(values).getColumnCount();
	             will(returnValue(2));
	             one(values).getValue(0, 0);
	             will(returnValue(7.5));
	             one(values).getValue(0, 1);
	             will(returnValue(2.5));
	             one(values).getValue(1, 0);
	             will(returnValue(8.5));
	             one(values).getValue(1, 1);
	             will(returnValue(3.5));
	         }
	     });
	     double result = DataUtilities.calculateRowTotal(values, 1);
	     assertEquals(result, 12.0, .000000001d);
	}
	
	@Test
	public void testCalculateRowTotalForEmptyTable() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(0));
	        }
	    });
	    double result = DataUtilities.calculateRowTotal(values, 0);
	    assertEquals(result, 0.0, .000000001d);
	}
	
	@Test
	public void testCalculateRowTotalForSingleValue() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(1));
	            one(values).getValue(0, 0);
	            will(returnValue(10.0));
	        }
	    });
	    double result = DataUtilities.calculateRowTotal(values, 0);
	    assertEquals(result, 10.0, .000000001d);
	}

	@Test
	public void testCalculateRowTotalForNegativeValues() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(4));
	            one(values).getValue(0, 0);
	            will(returnValue(-2.0));
	            one(values).getValue(0, 1);
	            will(returnValue(-3.0));
	            one(values).getValue(0, 2);
	            will(returnValue(-4.0));
	            one(values).getValue(0, 3);
	            will(returnValue(-5.0));
	        }
	    });
	    double result = DataUtilities.calculateRowTotal(values, 0);
	    assertEquals(result, -14.0, .000000001d);
	}
	
	@Test(expected = ExpectationError.class)
	public void testCalculateRowTotalForInvalidRow() {
	    values = mockingContext.mock(Values2D.class);
	    mockingContext.checking(new Expectations() {
	        {
	            one(values).getColumnCount();
	            will(returnValue(2));
	        }
	    });
	    // this will throw an exception because the row index is out of bounds
	    DataUtilities.calculateRowTotal(values, 2);
	}
	
	
	
	// testing method: GetCumulativePercentages()
	@Test
	public void testGetCumulativePercentagesThreeValues() {		
		keyVal = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {
			{
				allowing(keyVal).getItemCount();
				will(returnValue(3));
				
				allowing(keyVal).getKey(0);
				will(returnValue("0"));
				allowing(keyVal).getKey(1);
				will(returnValue("1"));
				allowing(keyVal).getKey(2);
				will(returnValue("2"));
				
				allowing(keyVal).getIndex("0");
				will(returnValue(0));
				allowing(keyVal).getIndex("1");
				will(returnValue(1));
				allowing(keyVal).getIndex("2");
				will(returnValue(2));
				
				allowing(keyVal).getValue(0);
				will(returnValue(5));
				allowing(keyVal).getValue(1);
				will(returnValue(9));
				allowing(keyVal).getValue(2);
				will(returnValue(2));
			}
		});
		
		  KeyedValues result = DataUtilities.getCumulativePercentages(keyVal);
		  
		  assertEquals(0.3125,result.getValue(0).doubleValue(),  .000000001d);
		  assertEquals(0.875,result.getValue(1).doubleValue(),  .000000001d);
		  assertEquals(1.0,result.getValue(2).doubleValue(),  .000000001d);
	}
	
	@Test
	public void testGetCumulativePercentagesSingleValue() {		
		keyVal = mockingContext.mock(KeyedValues.class);
		
		mockingContext.checking(new Expectations() {
			{
				allowing(keyVal).getItemCount();
				will(returnValue(1));
				
				allowing(keyVal).getKey(0);
				will(returnValue("0"));
				
				allowing(keyVal).getIndex("0");
				will(returnValue(0));
				
				
				allowing(keyVal).getValue(0);
				will(returnValue(5));
				
			}
		});
		
		  KeyedValues result = DataUtilities.getCumulativePercentages(keyVal);
		  
		  assertEquals(0.3125,result.getValue(0).doubleValue(),  .000000001d);
		  
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetCumulativePercentagesNullData() {
		DataUtilities.getCumulativePercentages(null);
	}


	
	
	@After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }
}
