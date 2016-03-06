/**
* @author Rodrigo Nicoletti
* @since 2016-03-06
**/

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class GUI {

	public static JFrame frame;
	
	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	
	private static ArrayList<String[]> courses;
	
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
				    	//DataReader dr = new DataReader();
				    	//courses = dr.readFileToArrayList(inFile);
				    	DataReader dr = new DataReader(inFile);
				    	dr.loadData();
				    	courses = dr.getArrayList();
					} catch (Exception e) {
						System.out.println("There is a problem with the file.");
						e.printStackTrace();
					}
				    
				}
				////////////////////////////////////////////////////////
				//prints the array list to test, delete it later
				
				for(int i = 0; i < courses.size(); i++){
					for(int j = 0; j < courses.get(i).length; j++){
						System.out.print(courses.get(i)[j] + " ");
					}
					System.out.println();
				}
				
				////////////////////////////////////////////////////////
			}
		});
		
		
		frame.add(btnLoadFile);
		
		frame.setVisible(true);
	}
}