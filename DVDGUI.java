import javax.swing.*;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.io.IOException;

/**
 *  This class is an implementation of DVDUserInterface
 *  that uses JOptionPane to display the menu of command choices. 
 */

public class DVDGUI implements DVDUserInterface {
	 
	 private DVDCollection dvdlist;
	 private JFrame fr;
	 private JPanel panel;
	 JList dvdJList;
	 JScrollPane dvdListPanel;
	 String titleText = "Title: ";
	 String ratingText = "Rating: ";
	 String runtimeText = "Runtime: ";
	 JLabel titleLabel, ratingLabel, runtimeLabel;
	 JLabel dvdImage;
	 
	 
	 public DVDGUI(DVDCollection dl)
	 {
		 dvdlist = dl;
		 Components();
	 }
	 
	 public void Components() {
		 
		 
		 fr=new JFrame("DVDGUI");
		 //For exit button
		 fr.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		 JPanel list = new JPanel();
		 fr.add(list);
		 
		//**DVD Info**//
		 Box infoBox = Box.createVerticalBox();
		 titleLabel = new JLabel(titleText);
		 infoBox.add(titleLabel);
		 ratingLabel = new JLabel(ratingText);
		 infoBox.add(ratingLabel);
		 runtimeLabel = new JLabel(runtimeText);
		 infoBox.add(runtimeLabel);
		 JButton modifyButton = new JButton("Modify DVD");
		 //Adding the eventListener, whenever user click it, there will be "Modify Panel"
		 modifyButton.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 try {
					 String title = dvdlist.getDVDArray()[dvdJList.getSelectedIndex()].getTitle();
					 addOrModifyDVD(title);
				 } catch (Exception error) {
					 
				 }
				 
	        }
		 });
		//**Option Buttons Panel**//
		 Box buttonBox = Box.createVerticalBox();
		 
		 //Adding new DVD" button
		 JButton add = new JButton("Add New DVD");
		 add.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 addOrModifyDVD();
	        }
		 });
		 buttonBox.add(add);
		 
		 //''Remove DVD" button
		 JButton remove = new JButton("Remove DVD");
		 remove.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 doRemoveDVD();
	        }
		 });
		 buttonBox.add(remove);
		 
		//''Rating DVD" button
		 JButton rating = new JButton("Get DVD List With Rating");
		 rating.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 doGetDVDsByRating();
	        }
		 });
		 buttonBox.add(rating);
		 
		//''Runningtime DVD" button
		 JButton runTime = new JButton("Total Running Time");
		 runTime.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 doGetTotalRunningTime();
	        }
		 });
		 buttonBox.add(runTime);
		 
		//''Save DVD" button
		 JButton saveBtn = new JButton("Save & Close");
		 saveBtn.addActionListener(new ActionListener() {
			 public void actionPerformed(ActionEvent e) {
				 doSave();
	        }
		 });
		 buttonBox.add(saveBtn);
		 buttonBox.add(Box.createVerticalStrut(10));
		 
		 list.add(buttonBox);
		 
		 
		 fr.setVisible(true);
		 fr.pack();
		 
		//**DVD List**//
		 dvdJList = new JList();
		 dvdListPanel = new JScrollPane(dvdJList);
		 refreshDVDJList();
		 dvdJList.setSelectedIndex(0);
		 updateDVDInfo(0);
		 list.add(dvdListPanel);
		 //Event Listner whenever the dvd ListPanel need to pop-up
		 dvdJList.addListSelectionListener(new ListSelectionListener() {
			 public void valueChanged(ListSelectionEvent x) {
				 if (!x.getValueIsAdjusting()) {
					 try {
						 updateDVDInfo(dvdJList.getSelectedIndex());
					 } catch (Exception e) {
						 dvdJList.setSelectedIndex(0);
						 updateDVDInfo(0);
					 }
				 }
			 }
		 });
		 
		 
	 }
	 
	 public void processCommands(){
		 
	 }
	 
	private void updateDVDInfo(int index) {
		 DVD selected = dvdlist.getDVDArray()[index];
		 titleLabel.setText(titleText + selected.getTitle());
		 ratingLabel.setText(ratingText + selected.getRating());
		 runtimeLabel.setText(runtimeText + selected.getRunningTime());
		 fr.pack();
	 }
	 
	
	 private void refreshDVDJList() {
		 dvdJList.setListData(dvdlist.getDVDArray());
	 }
	
	private void addOrModifyDVD() {
		addOrModifyDVD(null);
	}
	
	//Function to add or modify 
	private void addOrModifyDVD(String title) {

		// Request the title
		if(title == null)
			title = JOptionPane.showInputDialog(fr, "Enter title");
		if (title == null) {
			return;		
		}
		title = title.toUpperCase();
		
		
		
		
		// Request the rating
		String rating = JOptionPane.showInputDialog(fr, "Enter rating for " + title);
		if (rating == null) {
			return;		
		}
		rating = rating.toUpperCase();
		
		
		// Request the running time
		String time = JOptionPane.showInputDialog(fr, "Enter running time for " + title);
		if (time == null) {
		}

		dvdlist.addOrModifyDVD(title, rating, time);
		refreshDVDJList();	
	}
	
	private void doRemoveDVD() {

		// Request the title
		String title = JOptionPane.showInputDialog("Enter title");
		if (title == null) {
			return;
		}
		title = title.toUpperCase();
		
       // Remove the matching DVD if found
        dvdlist.removeDVD(title);
        refreshDVDJList();
	}
	
	
	private void doGetDVDsByRating() {

		// Request the rating
		String rating = JOptionPane.showInputDialog("Enter rating");
		if (rating == null) {
			return;		
		}
		rating = rating.toUpperCase();
		String results = dvdlist.getDVDsByRating(rating);
	       if(results == "") {
	    	   results = "No DVDs with this rating " + rating;
	       }
	    JOptionPane.showMessageDialog(fr, results);
		
		
//        String results = dvdlist.getDVDsByRating(rating);
//        System.out.println("DVDs with rating " + rating);
//        System.out.println(results);

	}

    private void doGetTotalRunningTime() {
    	int total = dvdlist.getTotalRunningTime();
        System.out.println("Total Running Time of DVDs: ");
        System.out.println(total);
                
    }
        

	private void doSave() {
		
		dvdlist.save();
		System.exit(0);
		
	}
	
}