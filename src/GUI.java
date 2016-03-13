/**
* @author Rodrigo Nicoletti
* @since 2016-03-06
**/

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Dimension;

public class GUI {

	public static JFrame frame;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static TimeTablePanel ttp;
	private static ArrayList<Course> courses;
	private static TimeTable timeTable;

	public static void main(String args[]){

		UIManager.installLookAndFeel("the look and feel",
				"com.sun.java.swing.plaf.gtk.GTKLookAndFeel");

		System.out.println(UIManager.getSystemLookAndFeelClassName());

		try {
            // Set cross-platform Java L&F (also called "Metal")
        UIManager.setLookAndFeel(
            "com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
    }
    catch (UnsupportedLookAndFeelException e) {
       // handle exception
			 //System.out.println(e.toString());
    }
    catch (ClassNotFoundException e) {
       // handle exception
			 //System.out.println(e.toString());
    }
    catch (InstantiationException e) {
       // handle exception
			 //System.out.println(e.toString());
    }
    catch (IllegalAccessException e) {
       // handle exception
			 //System.out.println(e.toString());
    }

		startGUI();
		guiManager();
	}

	public static void startGUI(){
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Scheduling Assistant Designer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
	}


	//@SuppressWarnings({"rawtypes","unchecked"})
	public static void guiManager(){



		ttp = new TimeTablePanel();
		ttp.setTimeTable(timeTable);
		JScrollPane scrollPane = new JScrollPane(ttp);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		//resoning behind minamum width and height
		//100px for the tims on the side * 120xp width for each day.
		//40px on top for day of the week * 20 px down for each 30 minuts (this may be to small)
		ttp.setPreferredSize(new Dimension(940, 540));

		JPanel options = new JPanel();
		JScrollPane optionsSP = new JScrollPane(options);
		optionsSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    optionsSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		optionsSP.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		options.setPreferredSize(new Dimension(250, 540));

		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {

				File inFile = null;
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(fileChooser);

				if (result == JFileChooser.APPROVE_OPTION){
				    inFile = fileChooser.getSelectedFile();
				    try {
				    	DataReader dr = new DataReader(inFile);
				    	dr.loadData();
				    	dr.makeCourseArray();
				    	courses = dr.getCourseArrayList();
					} catch (Exception e) {
						System.out.println("There is a problem with the file.");
						e.printStackTrace();
					}
				}
				////////////////////////////////////////////////////////
				//prints all courses in the arraylist courses to a file called output.txt
				/*try {
					FileWriter out = new FileWriter(new File("output.txt"));
					for(int i = 0; i < courses.size(); i++){
						out.write(courses.get(i).toString());
						out.write("\n");
					}
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}*/
				////////////////////////////////////////////////////////
			}
		});
		JPanel loadFile = new JPanel(new BorderLayout());
		loadFile.add(btnLoadFile,BorderLayout.CENTER);
		loadFile.setMaximumSize(new Dimension(250, 30));

		//Make Time Table buttnon
		JButton btnMakeTimeTable = new JButton("Display Selected Courses");
		btnMakeTimeTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				timeTable = new TimeTable("Test Time Table", courses);
				timeTable.prepareCourseDrawInfo();
				//System.out.println("Making Time Table");
				ttp.setTimeTable(timeTable);
				ttp.repaint();
			}
			});
		JPanel makeTimeTable = new JPanel(new BorderLayout());
		makeTimeTable.add(btnMakeTimeTable,BorderLayout.CENTER);
		makeTimeTable.setMaximumSize(new Dimension(250, 30));

		JLabel lbSearch = new JLabel("Search ");
		JTextField tfSearch = new JTextField(20);
		JPanel search = new JPanel(new BorderLayout());
		search.add(lbSearch, BorderLayout.WEST);
		search.add(tfSearch, BorderLayout.EAST);
		search.setMaximumSize(new Dimension(250, 30));

		String[] test= {"test1", "test1as", "tesdas1", "tescgt1", "tesgdfgt1", "test1jh", "tesjgfhht1",
		"tesasdast1", "testdfs1", "tdsfsdst1", "tedsfst1", "teszdsfdgfhdgfhgfdghfdghdgfghfdghgfhgfderetrxcvc1", "thjgfest1", "teszcvbt1", "test11"};
		JList<String> courseList = new JList<String>(test);
		JScrollPane courseListSP = new JScrollPane();
		courseListSP.getViewport().add(courseList);
    courseListSP.setPreferredSize(new Dimension(250, 200));

		JButton btnAdd = new JButton("Add Course");
		JButton btnDel = new JButton("Del Course");
		JPanel editP = new JPanel(new BorderLayout());
		editP.add(btnAdd, BorderLayout.NORTH);
		editP.add(btnDel, BorderLayout.CENTER);
		editP.setMaximumSize(new Dimension(250, 60));

		JList<String> timeTableList = new JList<String>(test);
		JScrollPane timeTableListSP = new JScrollPane();
		timeTableListSP.getViewport().add(timeTableList);
    timeTableListSP.setPreferredSize(new Dimension(250, 200));

		options.add(loadFile);
		options.add(search);
		options.add(courseListSP);
		options.add(editP);
		options.add(timeTableListSP);
		options.add(makeTimeTable);

		frame.add(options, BorderLayout.WEST);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
