/**
* @author Rodrigo Nicoletti
* @author Kier Lindsay
* @since 2016-03-06
**/

//import seaglasslookandfeel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentListener;
import javax.swing.event.DocumentEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ListModel;
//import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings({"rawtypes", "unchecked"})
public class GUI {

	private static JFrame frame;
	private static CourseManager cm;
	private static String[] arrCourses = {""};
	private static JList<String> courseList;
	private static JList<String> timeTableList;
	private static DefaultListModel<String> timeTableListModel = new DefaultListModel<String>();
	private static DefaultListModel<String> courseListModel = new DefaultListModel<String>(); //this is for the upper list

	private static boolean fileLoaded = false;

	private static final int WIDTH = 400;
	private static final int HEIGHT = 400;
	private static TimeTablePanel ttp;
	private static ArrayList<Course> courses;

	private static JComboBox cbSubject;
	private static JComboBox cbLevel;
	private static JComboBox cbLocation;
	private static JComboBox cbTeacher;
	private static String[] levels = {"All levels", "1", "2", "3", "4", "5", "6", "7"};

	private static JTextField tfSearch;

	private static TimeTable timeTable;
	private static JTextArea taConflictBox;



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






	//@SuppressWarnings({"rawtypes","unchecked"})
	public static void guiManager(){
		if(System.getProperty("os.name").equals("Linux")) {
			try {
	    	UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e) {
	    	e.printStackTrace();
			}
		}

		ttp = new TimeTablePanel();
		ttp.setTimeTable(timeTable);
		JScrollPane ttpSP = new JScrollPane(ttp);
		ttpSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ttpSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		ttpSP.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);

		//reasoning behind minimum width and height
		//100px for the times on the side * 120px width for each day.
		//40px on top for day of the week * 20px down for each 30 minutes (this may be to small)
		ttp.setPreferredSize(new Dimension(940, 540));


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
						if(inFile.getName().lastIndexOf(".") != -1 && inFile.getName().lastIndexOf(".") != 0){
                  if(inFile.getName().substring(inFile.getName().lastIndexOf(".")+1).equals("csv")){

						    try {
						    	DataReader dr = new DataReader(inFile);
						    	dr.loadData();
						    	dr.makeCourseArray();
						    	courses = dr.getCourseArrayList();
						    	cm = new CourseManager(courses);

						    	displayAllCourses();

						    	fileLoaded = true;

							} catch (Exception e) {
								System.out.println("There is a problem with the file.");
								e.printStackTrace();
							}//CATCH
						}//IF
					}//if
				}
			}
		});

		JPanel loadFile = new JPanel(new BorderLayout());
		loadFile.add(btnLoadFile,BorderLayout.CENTER);
		loadFile.setMaximumSize(new Dimension(250, 30));




		//Make Time Table button
		//DISPLAYS ALL SELECTED COURSES
		JButton btnMakeTimeTable = new JButton("Display Selected Courses");
		btnMakeTimeTable.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				if(fileLoaded){
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
			}
		});



		JPanel makeTimeTable = new JPanel(new BorderLayout());
		makeTimeTable.add(btnMakeTimeTable,BorderLayout.CENTER);
		makeTimeTable.setMaximumSize(new Dimension(250, 30));



		//JLabel lbSearch = new JLabel("Search");
		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});

		tfSearch = new JTextField(11);

		tfSearch.getDocument().addDocumentListener(new DocumentListener() {
		public void changedUpdate(DocumentEvent e){
			//nothing :D
		}
		public void insertUpdate(DocumentEvent e){
			search();
		}
		public void removeUpdate(DocumentEvent e){
			search();
		}
});

		JPanel searchP = new JPanel(new FlowLayout());
		searchP.add(btnSearch);
		searchP.add(tfSearch);
		searchP.setMaximumSize(new Dimension(250, 30));

		//you have to yous the same type of things as for the JList insted of a string array.
		//Start of the Filter Box
		String[] subjectStrings = {"All", "ANTH", "ASTR", "BCMB", "BIOL", "CHEM", "COMM", "CPSC", "ECON", "ENGL", "ENGR", "ENPL",
				"ENSC", "ENVS", "FNST", "FSTY", "GEOG", "HHSC", "HIST", "IASK", "INTS", "MATH", "MCPM", "NREM", "NRES",
				"NURS", "ORTM", "PHIL", "PHYS", "POLS", "PSYC", "SOCW", "STAT", "WMST"};
		//String[] levels = {"All levels", "1", "2", "3", "4", "5", "6", "7"};
		cbSubject = new JComboBox(subjectStrings);
		cbSubject.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileLoaded){
					String s = cbSubject.getSelectedItem().toString();
					displaySubjects(s);
					setComboBoxLevel(0);
				}
			}

		});



		cbLevel = new JComboBox(levels);
		cbLevel.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(fileLoaded){
					String s = cbLevel.getSelectedItem().toString();

					if(s.equals("All levels")){
						displaySubjects(cbSubject.getSelectedItem().toString());
					}
					else{
						displaySubjects(cbSubject.getSelectedItem().toString());
						ArrayList<String> displayedCourses = getListContent();
						courseListModel.removeAllElements();
						for(int i=0;i<displayedCourses.size();i++){
							if(displayedCourses.get(i).substring(4, 5).equals(s))
								courseListModel.addElement(displayedCourses.get(i));
						}

						courseList.setModel(courseListModel);
					}
				}
			}
		});



		JPanel filterP = new JPanel(new GridLayout(1, 2));
		filterP.add(cbSubject);
		filterP.add(cbLevel);
		filterP.setMaximumSize(new Dimension(250, 35));

		courseList = new JList<String>(arrCourses);
		//courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane courseListSP = new JScrollPane();
		courseListSP.getViewport().add(courseList);
		courseListSP.setPreferredSize(new Dimension(250, 200));




		JButton btnAdd = new JButton("Add Course");
		btnAdd.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				ArrayList<String> addList = (ArrayList) courseList.getSelectedValuesList();
				if(fileLoaded) {
					boolean shouldAdd;
					for(int i = 0; i < addList.size(); i++){
						shouldAdd = true;
						for(int j = 0; j < timeTableListModel.size(); j++){
							if(timeTableListModel.getElementAt(j).equals(addList.get(i))){
								shouldAdd = false;
								break;
							}
						}
						if(shouldAdd) {
							timeTableListModel.addElement(addList.get(i));
						}
					}
					timeTableList.setModel(timeTableListModel);
				}
			}
		});

		JButton btnAddAll = new JButton("Add All");
		btnAddAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				//timeTableListModel.addElement(courseList.getSelectedValue());
				ListModel<String> tempModel = courseList.getModel();
				for(int i = 0; i < tempModel.getSize(); i++) {
					timeTableListModel.addElement(tempModel.getElementAt(i));
				}
				timeTableList.setModel(timeTableListModel);
			}
		});

		JPanel addP = new JPanel(new GridLayout(1,2));
		addP.add(btnAdd);
		addP.add(btnAddAll);
		addP.setMaximumSize(new Dimension(250, 35));

		JButton btnDel = new JButton("Del Course");
		btnDel.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				int[] delList = timeTableList.getSelectedIndices();
				int fkBugs = 0;
				for(int i = 0; i < delList.length; i++){
					timeTableListModel.removeElementAt(delList[i]-fkBugs);
					fkBugs++;
				}
				timeTableList.setModel(timeTableListModel);
			}
		});
		JButton btnDelAll = new JButton("Del All");
		btnDelAll.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				timeTableListModel = new DefaultListModel<String>();
				timeTableList.setModel(timeTableListModel);
			}
		});

		JPanel delP = new JPanel(new GridLayout(1,2));
		delP.add(btnDel);
		delP.add(btnDelAll);
		delP.setMaximumSize(new Dimension(250, 35));


		String[] emptyDisplay = {""};
		timeTableList = new JList<String>(emptyDisplay);
		//timeTableList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane timeTableListSP = new JScrollPane();
		timeTableListSP.getViewport().add(timeTableList);
		timeTableListSP.setPreferredSize(new Dimension(250, 560));


		taConflictBox = new JTextArea();
		taConflictBox.setEditable(false);
		JScrollPane conflictBoxSP = new JScrollPane();
		conflictBoxSP.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		conflictBoxSP.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		conflictBoxSP.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
		taConflictBox.setMinimumSize(new Dimension(250, 100));
		//conflictBoxSP.setMaximumSize(new Dimension(100000, 400));
		conflictBoxSP.getViewport().add(taConflictBox);

		JSplitPane ttpConflictBoxSplitPane =
						new JSplitPane(JSplitPane.VERTICAL_SPLIT, ttpSP, conflictBoxSP);
		ttpConflictBoxSplitPane.setOneTouchExpandable(true);
		ttpConflictBoxSplitPane.setDividerLocation(560);
		ttpConflictBoxSplitPane.setResizeWeight(.8d);


		JPanel optionsTopP = new JPanel();
		optionsTopP.setPreferredSize(new Dimension(250, 400));
		optionsTopP.setLayout(new BoxLayout(optionsTopP, BoxLayout.Y_AXIS));
		optionsTopP.add(loadFile);
		optionsTopP.add(filterP);
		optionsTopP.add(searchP);
		optionsTopP.add(courseListSP);
		optionsTopP.add(addP);

		JPanel optionsBottomP = new JPanel();
		optionsBottomP.setPreferredSize(new Dimension(250, 240));
		optionsBottomP.setLayout(new BoxLayout(optionsBottomP, BoxLayout.Y_AXIS));
		optionsBottomP.add(timeTableListSP);
		optionsBottomP.add(delP);
		optionsBottomP.add(makeTimeTable);

		JSplitPane optionsSplitPane =
				new JSplitPane(JSplitPane.VERTICAL_SPLIT, optionsTopP, optionsBottomP);
		optionsSplitPane.setOneTouchExpandable(true);
		optionsSplitPane.setDividerLocation(420);
		optionsSplitPane.setResizeWeight(.64d);
		frame.add(optionsSplitPane, BorderLayout.WEST);
		frame.add(ttpConflictBoxSplitPane, BorderLayout.CENTER);
		//frame.add(conflictBoxSP, BorderLayout.SOUTH);
		frame.pack();
		frame.setVisible(true);
	}

	private static void search(){
		String searchString = tfSearch.getText(); //ss = serchString
		//System.out.println(tfSearch.getText());
		DefaultListModel<String> searchListModel = new DefaultListModel<String>();

		String courseListItem;
		boolean isValid;
		for(int i = 0; i < courseListModel.getSize(); i++) {
			courseListItem = courseListModel.get(i);
			isValid = superSearch(searchString, courseListItem);
			if(isValid) {
				searchListModel.addElement(courseListModel.get(i));
			}
		}
		courseList.setModel(searchListModel);
	}

	private static boolean superSearch(String ss, String sts) {
		//ss is search string and sts is string to search
		boolean sucsess = false;
		boolean smallSuc;
		ss = ss.toLowerCase();
		sts = sts.toLowerCase();
		for(int i = 0; i < sts.length()-ss.length()+1; i++) {
			smallSuc = true;
			for(int j = 0; j < ss.length(); j++) {
				if(ss.charAt(j)!=sts.charAt(i+j)) {
					smallSuc = false;
					break;
				}
			}
			if(smallSuc) {
				sucsess = true;
				break;
			}
		}
		return sucsess;
	}



	public static void displayAllCourses(){
		courseListModel.removeAllElements();
		for(int i = 0; i < courses.size(); i++)
			courseListModel.addElement(
						courses.get(i).getCouseID() + "-"
			 		+ courses.get(i).getComponetID() + " "
			 		+ courses.get(i).getLocation().getRoomNumber() + " "
			 		+ courses.get(i).getProfessorName()
			);

    	courseList.setModel(courseListModel);
	}

	public static ArrayList<String> getListContent(){
		ArrayList<String> selectedCourses = new ArrayList<String>();

		for(int i = 0; i < courseListModel.getSize(); i++){
			selectedCourses.add(courseListModel.getElementAt(i));
		}

		return selectedCourses;
	}

	public static void displaySubjects(String s){
		if(s.equals("All"))
			displayAllCourses();
		else{
			ArrayList<String> arrSubject = new ArrayList<String>();

			arrSubject = cm.getSubjectCoursesArrayList(s);

			courseListModel.removeAllElements();
			for(int i = 0; i < arrSubject.size(); i++)
				courseListModel.addElement(arrSubject.get(i));
			courseList.setModel(courseListModel);
		}

	}

	private static void setComboBoxLevel(int l) {
		cbLevel.setSelectedItem(levels[l]);
	}
}
