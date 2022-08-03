import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class DVDTest {
	private DVD d1,d2,d3,d4;
	
	@Before
	public void setUp() {
		d1=new DVD("Auntie Mame","NR",123);
		d2=new DVD("The Gentlemen","R",97);
		d3=new DVD("Fantastic Beasts","PG-13",89);
		d4=new DVD("American Underdog","PG",103);
	}
	
	
	
	
	@Test
	public void testConstruc() {
		assertEquals("AUNTIE MAME",d1.getTitle());
		assertEquals("THE GENTLEMEN",d2.getTitle());
		assertEquals("FANTASTIC BEASTS",d3.getTitle());
		assertEquals("AMERICAN UNDERDOG",d4.getTitle());
		assertEquals("NR",d1.getRating());
		assertEquals("R",d2.getRating());
		assertEquals("PG-13",d3.getRating());
		assertEquals("PG",d4.getRating());
		assertEquals(123,d1.getRunningTime());
		assertEquals(97,d2.getRunningTime());
		assertEquals(89,d3.getRunningTime());
		assertEquals(103,d4.getRunningTime());
	}
	
	@Test
	//Testing GetTitle
	public void testGetTitle() {
		DVD test = new DVD("Auntie Mame","R",123);
		assertEquals("AUNTIE MAME", test.getTitle());
	}
	
	@Test
	// Testing GetRating
	public void testGetRating() {
		DVD test = new DVD("Auntie Mame","R",123);
		assertEquals("R", test.getRating());
	}
	
	@Test
	// Testing Running 
	public void testGetRunningTime() {
		DVD test = new DVD("Auntie Mame","R",123);
		assertEquals(123, test.getRunningTime());
	}
	
	@Test
	// Testing Set Title
	public void testSetTitle() {
		d1.setTitle("This is a new Title");
		assertEquals("THIS IS A NEW TITLE",d1.getTitle());
		d1.setTitle("Auntie Mame");
		assertEquals("AUNTIE MAME",d1.getTitle());
	}
	@Test
	// Testing SetRating
	public void testSetRating() {
		d2.setRating("NR");
		assertEquals("NR",d2.getRating());
		d1.setRating("R");
		assertEquals("R",d1.getRating());
	}
	@Test
	// Testing RunningTime 
	public void testSetRunningTime() {
		d3.setRunningTime(1234);
		assertEquals(1234,d3.getRunningTime());
		d3.setRunningTime(89);
		assertEquals(89,d3.getRunningTime());
	}
	@Test
	// Testing ToString
	public void testToString() {
		assertEquals("AUNTIE MAME/NR/123min",d1.toString());
		assertEquals("THE GENTLEMEN/R/97min",d2.toString());
		assertEquals("FANTASTIC BEASTS/PG-13/89min",d3.toString());
		assertEquals("AMERICAN UNDERDOG/PG/103min",d4.toString());
	}
}