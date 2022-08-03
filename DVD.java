import java.util.Comparator;

public class DVD{

	// Fields:

	private String title;		// Title of this DVD
	private String rating;		// Rating of this DVD
	private int runningTime;	// Running time of this DVD in minutes
	
//	//Constructors
//	public DVD(){
//		this.title = "";
//		this.rating = "";
//		this.runningTime = 0;
//	}
	
	
	
	public DVD(String dvdTitle, String dvdRating, int dvdRunningTime) 
	{
		setTitle(dvdTitle);
	    setRating(dvdRating);
	    setRunningTime(dvdRunningTime);
	}
	
	public String getTitle() 
	{
		return title;	// STUB: Remove this line.
	}
	
	public String getRating() 
	{

		return rating;	// STUB: Remove this line.
	}
	
	public int getRunningTime() 
	{



		return runningTime;	// STUB: Remove this line.
	}

	public void setTitle(String newTitle) {
		title=newTitle.toUpperCase();
	}

	public void setRating(String newRating) {
		rating=newRating.toUpperCase();
	}

	public void setRunningTime(int newRunningTime) {
		runningTime=newRunningTime;
	}

	public String toString() {
		return getTitle() + "/" + getRating() + "/" + getRunningTime()+"min";	// STUB: Remove this line.
	}
	
}