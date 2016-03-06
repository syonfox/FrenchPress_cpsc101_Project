/**
*date: January 2016
* @author Kier Lindsay
**/



import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedWriter;



class DataReader {
  /** file is a file object that links to the file's path **/
  private File file;
  /** that ArrayList filelines is uses to store a file within the fileManager **/
  private ArrayList<String[]> fileLines;

  private ArrayList<Course> courses;
  /** Constuctor takes a file path as a string and makes the file, it also initalizes fileList
  *@param filePath the path to the data File
  */

  public DataReader(File inFile) {

    this.file = inFile;

    fileLines = new ArrayList<String[]>();
    courses = new ArrayList<Course>();
  }

  public DataReader(){
	  //leave it here for now
	  }


  /** Loads a file into an ArrayList (fileLines) **/
  public void loadData() {
    if(fileLines.isEmpty()) {
      try {
	BufferedReader fileIn = new BufferedReader(new FileReader(file));
	String[] lineData;
	String line = fileIn.readLine();
	while(line != null) {
	  lineData = new String[13];
	  for(int i = 0; i < 13; i++ ) {
	    lineData[i] = "";
	  }
	  for(int i = 0; i <line.split(",",0).length; i++ ) {
	    lineData[i]= line.split(",",0)[i];
	  }

	  //lineData = line.split(",");

	 // System.out.println(lineData[0]+lineData[1]+lineData[2]+lineData[3]+lineData[4]+lineData[5]+lineData[6]+lineData[7]+lineData[8]+lineData[9]+lineData[10]+lineData[11]+lineData[12]);
	  if( !lineData[4].equals("") ||  !lineData[7].equals("") ) {
	    fileLines.add(lineData);
	  }
	  line = fileIn.readLine();
	}
  fileLines.remove(0);
  fileLines.remove(0);

      } catch(FileNotFoundException e) {
	System.out.println("Error: " + file.getAbsolutePath() + " File Not found");
	//here we could add somthing to make maby a popup if there is an error.
      } catch(IOException e) {
	System.out.println("Error: IO Exeption");
      }
    }
  }

  /** prints a file (must be loaded into an ArrayList first) **/
  public void printFile() {
    if(fileLines.isEmpty()) {
      System.out.print("Cannot Print File, If you jave not loaded the file or the file is empty/doesnt exist you canot print it.");
    } else {
      for(int i = 0; i < fileLines.size(); i++) {
	for(int j = 0; j < fileLines.get(i).length ; j++) {
	  System.out.print(fileLines.get(i)[j]+ " , ");
	}

	System.out.print("\n");
      }
    }
  }

  /** Returns the file as an ArrayList
  *@return a array list of all the lines in the file.
  */
  public ArrayList<String[]> getArrayList() {
    return fileLines;
  }

  public void makeCourseArray() {
    Course c = new Course();
    String courID;
    String compID;
    ArrayList<String> dow = new ArrayList<String>();

    Date sDate = new Date();
    Date eDate = new Date();
    String[] dateTemp;

    for(int i = 0; i < fileLines.size(); i++) {
      if(fileLines.get(0).equals(courID) && fileLines.get(1).equals(compID)) {
        if(fileLines.get(4).equals("")) {
          dow.add(fileLines.get(7));
        } else {
          dow.add(fileLines.get(4));
        }
      }
      else {
        courses.add(c);
        c = new Course();

        c.setCourseID(fileLines.get(0));
        c.setComponetId(fileLines.get(1));
        dateTemp = fileLines.get(2).split("-", 0);
        sDate.setYear(Intiger.parseInt(dateTemp[0]));
        sDate.setMonth(Intiger.parseInt(dateTemp[1]));
        sDate.setDay(Intiger.parseInt(dateTemp[2]));

        dateTemp = fileLines.get(3).split("-", 0);
        eDate.setYear(Intiger.parseInt(dateTemp[0]));
        eDate.setMonth(Intiger.parseInt(dateTemp[1]));
        eDate.setDay(Intiger.parseInt(dateTemp[2]));

        c.setCourseDate(new CourseDate(sDate, eDate));

        if(fileLines.get(4).equals("")) {

        } else {

        }
      }
    }

  }
}
