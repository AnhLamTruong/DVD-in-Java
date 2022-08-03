import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)

@SuiteClasses({
	DVDTest.class, DVDCollectionTest.class, DVDGUITest.class,DVDGUITest2.class
})
public class AllTests {
}