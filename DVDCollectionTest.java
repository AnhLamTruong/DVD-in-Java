import static org.junit.Assert.*;


import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;



public class DVDCollectionTest {
//	private DVD d1,d2,d3,d4;
	private DVDCollection dvds;
	private DVDCollection dvd2;
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final PrintStream originalErr = System.err;

	@Before
	public void setUp() throws Exception{
		dvds = new DVDCollection();
	}
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	    System.setErr(new PrintStream(errContent));
	}
	
	@After
	public void tearDown() throws Exception{
		System.setOut(originalOut);
	    System.setErr(originalErr);
	}
	@Test
	public void testConstructor() {
		assertEquals(0,dvds.getNumDVDs());
	}
	@Test
	public void testAddOrModifyDVD() {
		
		//Testing adding for the first dvd
		dvds.addOrModifyDVD("Auntie Mame", "R", "123");

		assertEquals(1,dvds.getNumDVDs());
		assertEquals("AUNTIE MAME/R/123min\n",dvds.toStringForTesting());
		
		
		//Testing adding for the second dvd
		dvds.addOrModifyDVD("The Gentlemen", "R", "97");
		assertEquals(2,dvds.getNumDVDs());
		
		assertEquals("AUNTIE MAME/R/123min\nTHE GENTLEMEN/R/97min\n",dvds.toStringForTesting());
			
	}
	
	@Test
	public void testRemoveDVD() {
		
		// Testing remove the dvd
		dvds.addOrModifyDVD("Auntie Mame", "R", "123");
		dvds.addOrModifyDVD("The Gentlemen", "R", "97");
		assertEquals(2,dvds.getNumDVDs());
		dvds.removeDVD("The Gentlemen");
		assertEquals(1,dvds.getNumDVDs());		
		
	}
	
	@Test
	// Testing get DVD by Rating
	public void testGetDVDsByRating() {
		dvd2 = new DVDCollection();
		dvd2.addOrModifyDVD("Auntie Mame", "R", "123");
		dvd2.addOrModifyDVD("AAA", "PG", "917");
		assertEquals("AUNTIE MAME/R/123min\n",dvd2.getDVDsByRating("R"));
	}
	@Test
	// Testing get total Running Time
	public void testGetTotalRunningTime() {
		dvds.addOrModifyDVD("HHH", "R", "127");
		dvds.addOrModifyDVD("ABD", "R", "917");
		assertEquals(1044, dvds.getTotalRunningTime());
	}
}
	
	