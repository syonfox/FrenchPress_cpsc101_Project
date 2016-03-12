/**
* @author Rodrigo Nicoletti
* @since 2016-03-06
**/

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JViewport;

public class GUI {

	public static JFrame frame;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static TimeTablePanel ttp;
	private static ArrayList<Course> courses;
	private static TimeTable timeTable;
	public static void main(String args[]){
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

	public static void guiManager(){
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
				try {
					FileWriter out = new FileWriter(new File("output.txt"));
					for(int i = 0; i < courses.size(); i++){
						out.write(courses.get(i).toString());
						out.write("\n");
					}
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				////////////////////////////////////////////////////////
			}
		});

		JButton btnMakeTimeTable = new JButton("Make TimeTable");
		btnMakeTimeTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				timeTable = new TimeTable("Test Time Table", courses);
				timeTable.prepareCourseDrawInfo();
				System.out.println("Making Time Table");
				ttp.setTimeTable(timeTable);
				ttp.repaint();
			}
			});


		ttp = new TimeTablePanel();
		ttp.setTimeTable(timeTable);
		JScrollPane scrollPane = new JScrollPane(ttp);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		//scrollPane.setBounds(0, 0, 840, 640);
		//resoning behind minamum width and height
		//100px for the tims on the side * 120xp width for each day.
		//40px on top for day of the week * 20 px down for each 30 minuts (this may be to small)

		JPanel options = new JPanel();
		options.setLayout(new BoxLayout(options, BoxLayout.Y_AXIS));

		options.add(btnLoadFile);
		options.add(btnMakeTimeTable);

		ttp.setPreferredSize(new Dimension(940, 540));

		frame.add(options, BorderLayout.WEST);
		frame.add(scrollPane, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
}
