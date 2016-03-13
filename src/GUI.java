/**
* @author Rodrigo Nicoletti
* @author Kier Lindsay
* @since 2016-03-06
**/

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;


public class GUI {

	private static JFrame frame;
	private static CourseManager cm;
	private static String[] arrCourses = {""};
	private static JList<String> courseList;
	private static JList<String> timeTableList;
	private static DefaultListModel<String> timeTableListModel = new DefaultListModel<String>();

	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static TimeTablePanel ttp;
	private static ArrayList<Course> courses;
	private static TimeTable timeTable;
	private static JTextArea taConflictBox;
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

		//reasoning behind minimum width and height
		//100px for the times on the side * 120px width for each day.
		//40px on top for day of the week * 20px down for each 30 minutes (this may be to small)
		ttp.setPreferredSize(new Dimension(940, 540));

		JPanel options = new JPanel();
		JScrollPane optionsSP = new JScrollPane(options);
		optionsSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		optionsSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		optionsSP.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		options.setPreferredSize(new Dimension(250, 540));

		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));




		//handles load file button
		JButton btnLoadFile = new JButton("Load File");
		btnLoadFile.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				File inFile = null;
				JFileChooser fileChooser = new JFileChooser();

				FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV FILES", "csv", "csv");
				fileChooser.setFileFilter(filter);

				fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				int result = fileChooser.showOpenDialog(fileChooser);

				if (result == JFileChooser.APPROVE_OPTION){
				    inFile = fileChooser.getSelectedFile();
				    try {
				    	DataReader dr = new DataReader(inFile);
				    	dr.loadData();
				    	dr.makeCourseArray();
				    	courses = dr.getCourseArrayList();
				    	cm = new CourseManager(courses);
				    	DefaultListModel<String> model = new DefaultListModel<String>();
				    	for(int i = 0; i < courses.size(); i++)
				    		model.addElement(courses.get(i).getCouseID() + " - " + courses.get(i).getComponetID());

				    	courseList.setModel(model);

					} catch (Exception e) {
						System.out.println("There is a problem with the file.");
						e.printStackTrace();
					}
				}
			}
		});

		JPanel loadFile = new JPanel(new BorderLayout());
		loadFile.add(btnLoadFile,BorderLayout.CENTER);
		loadFile.setMaximumSize(new Dimension(250, 30));




		//Make Time Table button
		JButton btnMakeTimeTable = new JButton("Display Selected Courses");
		btnMakeTimeTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> selectedCourses = new ArrayList<String>();

				for(int i = 0; i < timeTableListModel.getSize(); i++){
					selectedCourses.add(timeTableListModel.getElementAt(i));
				}

				ArrayList<Course> c = cm.toCourseArrayList(selectedCourses);


				timeTable = new TimeTable("Test Time Table", c);
				timeTable.cheackConfilcts();
				timeTable.prepareCourseDrawInfo();
				ttp.setTimeTable(timeTable);
				ttp.repaint();

				String[] tempConfilcts = timeTable.getConflicts();
				taConflictBox.setText("");
				for(int i = 0; i < tempConfilcts.length; i++) {
					taConflictBox.append(tempConfilcts[i]);
				}
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

		courseList = new JList<String>(arrCourses);
		JScrollPane courseListSP = new JScrollPane();
		courseListSP.getViewport().add(courseList);
		courseListSP.setPreferredSize(new Dimension(250, 200));








		JButton btnAdd = new JButton("Add Course");
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				timeTableListModel.addElement(courseList.getSelectedValue());
				timeTableList.setModel(timeTableListModel);
			}
		});

		JButton btnDel = new JButton("Del Course");
		btnDel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				timeTableListModel.removeElement(timeTableList.getSelectedValue());
				//timeTableListModel.addElement(courseList.getSelectedValue());
				timeTableList.setModel(timeTableListModel);
			}
		});



		JPanel editP = new JPanel(new BorderLayout());
		editP.add(btnAdd, BorderLayout.NORTH);
		editP.add(btnDel, BorderLayout.CENTER);
		editP.setMaximumSize(new Dimension(250, 60));

		String[] emptyDisplay = {""};
		timeTableList = new JList<String>(emptyDisplay);
		JScrollPane timeTableListSP = new JScrollPane();
		timeTableListSP.getViewport().add(timeTableList);
		timeTableListSP.setPreferredSize(new Dimension(250, 200));


		taConflictBox = new JTextArea();
		JScrollPane conflictBoxSP = new JScrollPane(options);
		conflictBoxSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		conflictBoxSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		conflictBoxSP.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		taConflictBox.setMinimumSize(new Dimension(250, 100));
		conflictBoxSP.setPreferredSize(new Dimension(250, 200));
		conflictBoxSP.getViewport().add(taConflictBox);




		options.add(loadFile);
		options.add(search);
		options.add(courseListSP);
		options.add(editP);
		options.add(timeTableListSP);
		options.add(makeTimeTable);

		frame.add(options, BorderLayout.WEST);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.add(conflictBoxSP, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}
}
