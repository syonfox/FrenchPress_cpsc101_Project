/**
* @author Rodrigo Nicoletti
* @since 2016-03-06
**/

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class GUI {

	public static JFrame frame;
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	private static ArrayList<Course> courses;
	
	public static void main(String args[]){
		startGUI();
		guiManager();
	}
	
	public static void startGUI(){
		frame = new JFrame();
		frame.setSize(WIDTH, HEIGHT);
		frame.setTitle("Scheduling Assistant Designer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
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
		
		
		frame.add(btnLoadFile);
		
		frame.setVisible(true);
	}
}