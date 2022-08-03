import java.io.*;
import java.util.Arrays;
import java.util.Comparator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DVDCollection {

	// Data fields
	
	/** The current number of DVDs in the array */
	private int numdvds;
	
	/** The array to contain the DVDs */
	private DVD[] dvdArray;
	
	/** The name of the data file that contains dvd data */
	private String sourceName;
	
	/** Boolean flag to indicate whether the DVD collection was
	    modified since it was last saved. */
	private boolean modified;
	
	/**
	 *  Constructs an empty directory as an array
	 *  with an initial capacity of 7. When we try to
	 *  insert into a full array, we will double the size of
	 *  the array first.
	 */
	public DVDCollection() {
		numdvds = 0;
		dvdArray = new DVD[7];
	}
	
	public DVD[] getDVDArray() {
		return dvdArray;
	}
	
	public int getNumDVDs(){
		return numdvds;
	}
	
	public int getArrayLength() {
		return dvdArray.length;
	}
	
	public String toStringForTesting() {
		String dvdLine= "";
		for (int i = 0; i < numdvds; i++) {
			dvdLine += (dvdArray[i].toString()+'\n'); 
			// Using loop to add new string to the variable                 
		}

		return dvdLine;	// Return that variable. 

	}
	
	public String toString() {
		// Return a string containing all the DVDs in the
		// order they are stored in the array along with
		// the values for numdvds and the length of the array.
		// See homework instructions for proper format.
		String dvdLine="";     // making a string variable to include the list message.
		dvdLine += ("numdvds="+numdvds + '\n');
		dvdLine += ("dvdArray.length="+dvdArray.length + '\n');
		for (int i = 0; i < numdvds; i++) {
			dvdLine += ("dvdArray["+i+"]="+dvdArray[i].toString()+'\n'); 
			// Using loop to add new string to the variable                 
		}

		return dvdLine;	// Return that variable. 
	}

	public void addOrModifyDVD(String title, String rating, String runningTime) {
		// NOTE: Be careful. Running time is a string here
		// since the user might enter non-digits when prompted.
		// If the array is full and a new DVD needs to be added,
		// double the size of the array first.
		title=title.toUpperCase(); //Must be uppercase to find the dvd
		if(rating.equals("NC-17") || rating.equals("R") || rating.equals("PG-13") || rating.equals("PG") || rating.equals("G")) {
		}
		else {
			return;
		}
		modified=true;
		try{
			int runtime=Integer.parseInt(runningTime); // Convert string to interger.
		}
		catch(NumberFormatException e) {
			System.out.println("Invalid Runnningtime");
		}
		int runtime=Integer.parseInt(runningTime);
		int foundIndex=-1; // The index to find the elements in the array.
		if(numdvds > 0) // This method is to find the index number of the title 
			for(int i = 0; i < numdvds; i++) { 
				if(dvdArray[i].getTitle()==title) {
					foundIndex=i;
				}
			}
		if (foundIndex>=0) { // If there's already have the same dvd at foundIndex
							 // return the messages
			dvdArray[foundIndex].setTitle(title);
	        dvdArray[foundIndex].setRating(rating);
	        dvdArray[foundIndex].setRunningTime(runtime);
	        System.out.println("You have a same dvd at " + foundIndex+'\n');

		}
		else {
			if(numdvds < dvdArray.length) { // If there is still aspot for new dvd
											// ADD IT ADD IT ADD IT !!!
				dvdArray[numdvds] = new DVD(title, rating, runtime);
		        System.out.println("[" + numdvds + "]" + dvdArray[numdvds].toString());
		        numdvds++;
		        // add new dvd
		        Arrays.sort(dvdArray,0,numdvds, new DVDTitle());

		    }
			else { // in the case there is no space, double the size
				System.out.println("NO SPACE, DOUBLE SIZE OF ARRAY");
	            doubleDVDSize();
	            System.out.println(toString());
			}
	}
}
		
	
	public void removeDVD(String title) {
		title=title.toUpperCase(); //Must be uppercase to find the dvd
		int foundIndex=-1; // The index to find the elements in the array.
		if(numdvds > 0) // This method is to find the index number of the title 
			for(int i = 0; i < numdvds; i++) { 
				if(dvdArray[i].getTitle().equals(title)) {
					foundIndex=i;
				}
			}
		 if(foundIndex >= 0) {
	           modified = true;
	           DVD[] newArr = new DVD[dvdArray.length - 1]; //Set a new Array to store
	           												//the old data.
	           System.arraycopy(dvdArray, 0, newArr, 0, foundIndex); // Copy string elements before foundIndex.
	           System.arraycopy(dvdArray, foundIndex + 1,newArr, foundIndex,dvdArray.length - foundIndex - 1); // Copy string elements after foundIndex.
	           dvdArray=newArr;
	           numdvds--;
	           Arrays.sort(dvdArray,0,numdvds, new DVDTitle());
		 }
		 else {
			 System.out.println("DVD not in the Array"); // Throw a string  HAHAHAHA
		 }		 
	}
	
	public String getDVDsByRating(String rating) {
		rating=rating.toUpperCase(); // Must be uppercase !!!
		String sameRate="";
		//a String to store an array
		for(int i = 0; i < numdvds; i++) {
			if(dvdArray[i].getRating().equals(rating)){
				sameRate += (dvdArray[i].toString() + '\n');
			}
		}
		return sameRate;	// STUB: Remove this line.

	}

	public int getTotalRunningTime() {
		int total = 0;
			// an interger to store a total of running time
	       for(int i = 0; i < numdvds; i++) {
	           total += dvdArray[i].getRunningTime();
	       }
	       return total;
	}

	
	public void loadData(String filename) {
		try {
			FileReader myFile= new FileReader(filename); // open the filename
			BufferedReader reader= new BufferedReader(myFile);
			String line;
			while((line=reader.readLine())!=null) { // read through each line
				String[] data=line.split(","); // store data "," by ","
				if(data.length != 3) {  // if the length of the data is different from 3
										// there is something wrong.
					System.out.println("Error: Invalid DVD\"" + line + "\""); 
					//the data in the file is corrupted, stop initializing the collection
				    //at the point of corruption
		            return;
		        }
				addOrModifyDVD(data[0], data[1], data[2]);
			}
			//reader.close();
			modified=false;
			sourceName=filename;
		}
		catch(IOException e){
			System.out.println("PEEP POOP !!!! FILE NOT FOUND !!!!!");
		}
	}
	
	public void save() {
		 try {
			 if(!modified){ // If there is no modification in the array, there no need to save !!
				 System.out.println("There is no modification in the array, there no need to save !!");
				 return;
			 }
	         FileWriter myFile = new FileWriter(sourceName);
	         if(numdvds > 0) { 	 // If there is a modification, just write the file :)
	        	 for(int i = 0; i < numdvds; i++) {
	        		 myFile.write(dvdArray[i].getTitle() + "," + dvdArray[i].getRating() + "," + dvdArray[i].getRunningTime()+'\n');
	             }
	         }
	         myFile.close();
	         System.out.println("Changes to " + sourceName + " saved.");
	         modified = false;
	     }
		 catch (IOException e) {
	           System.err.println("Error saving file: " + e);
	      }
	}

	// Additional private helper methods go here:
	private void doubleDVDSize() { // This method double dvd array size 
	    DVD[] newArray = new DVD[dvdArray.length * 2];
	    System.arraycopy(dvdArray, 0, newArray, 0, numdvds); // Copy the previous array to the new array
	    dvdArray=newArray; // Make the new array equal to to previous array
	}
}

// Sort comparator fo DVD objects !!!!!
class DVDTitle implements Comparator<DVD> {
	public int compare(DVD a, DVD b) {
		return a.getTitle().compareTo(b.getTitle());
	}
}

