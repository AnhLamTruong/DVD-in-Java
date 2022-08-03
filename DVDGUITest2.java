import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class DVDGUITest2 extends DVDGUITest {
	private DVDGUI emptyPanel;
    private DVDGUI tannerPanel;
    

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
	 
	 protected void setUP() throws Exception{
		 //Creating Component without dvdlist
		 emptyPanel.Components();
		 
		 //Creating Component with dvdlist
		 dvdlist.loadData("dvddata.txt");
		 tannerPanel.Components();
	 }
}
