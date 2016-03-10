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

import java.sql.Time;


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


  /** Loads a file into an ArrayList (fileLines) **/
  public void loadData() {
    if(fileLines.isEmpty()) {
      try {
        //new buffered reader
	      BufferedReader fileIn = new BufferedReader(new FileReader(file));
	      String[] lineData;//an array for each line in the file
	      String line = fileIn.readLine();//sets line to the first line in the file
	      while(line != null) {//while there are still lines left in the file
	        lineData = new String[13];//there are 13 items in the csf file
          //initalizes the lineData as ""
          for(int i = 0; i < 13; i++ ) {
	           lineData[i] = "";
	        }
          //puts the data from the csv into the lineData array
          for(int i = 0; i <line.split(",",0).length; i++ ) {
	          lineData[i]= line.split(",",0)[i];
	        }
          //debug line
	        // System.out.println(lineData[0]+lineData[1]+lineData[2]+lineData[3]+lineData[4]+lineData[5]+lineData[6]+lineData[7]+lineData[8]+lineData[9]+lineData[10]+lineData[11]+lineData[12]);
          //if the line has all of the needed info in it then it adds it
          if( !lineData[4].equals("") ||  !lineData[7].equals("") ) {
            fileLines.add(lineData);
          }
          //gets the next line
	        line = fileIn.readLine();
	      }
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

  public ArrayList<Course> getCourseArrayList() {
      return courses;

	  }


  public void makeCourseArray() {
	  Course c;

	  Date sDate = new Date();
	  Date eDate = new Date();
	  String[] arrSDate;
	  String[] arrEDate;


	  for(int i = 0; i < fileLines.size();i++){
		  c = new Course();

		  //setting courseID
		  c.setCourseID(fileLines.get(i)[0]);

		  //setting courseComponent
		  c.setComponetID(fileLines.get(i)[1]);

		  //setting start/end dates
		  arrSDate = fileLines.get(i)[2].split("-", 0);
		  arrEDate = fileLines.get(i)[3].split("-", 0);
		  sDate = new Date(
				  Integer.parseInt(arrSDate[0]),
				  Integer.parseInt(arrSDate[1]),
				  Integer.parseInt(arrSDate[2])
	    );
		  eDate =new Date(
				  Integer.parseInt(arrEDate[0]),
				  Integer.parseInt(arrEDate[1]),
				  Integer.parseInt(arrEDate[2])
			);
		  c.setDates(new CourseDate(sDate,eDate));


		  //setting day of the week
		  if(fileLines.get(i)[4].equals("")){
			  c.addDayOfWeek(fileLines.get(i)[7]);

        //seting the start
        c.setStartTime( getStartTime( fileLines.get(i)[8] ) );

        //seting the duration
        c.setDuration( getDuration( fileLines.get(i)[9] ) );
		  }//if

		  else if(fileLines.get(i)[7].equals("")){
			  c.addDayOfWeek(fileLines.get(i)[4]);

        //seting the start
        c.setStartTime( getStartTime( fileLines.get(i)[5] ) );

        //seting the duration
        c.setDuration( getDuration( fileLines.get(i)[6] ) );

		  }//else if

		  //setting buildingID
		  c.setLocation(new Location(fileLines.get(i)[10],fileLines.get(i)[11]));

      //setting profesor name
      c.setProfessorName(fileLines.get(i)[12]);

		  courses.add(c);
	  }//for


  }//makeCourseArray method

  @SuppressWarnings("deprecation")
  private Time getStartTime(String s) {
    //A temp array to hold the time split into its components
    String[] tempStartTime = s.split(":", 0);

    //returning start time based on the intigers from the tempStartTime[]
    return new Time(
        Integer.parseInt(tempStartTime[0]),
        Integer.parseInt(tempStartTime[1]),
        0
    );
  }

  private int getDuration(String s) {
    //Temp String[] to hold deuation components
    String[] tempDuration = s.split(":", 0);
    //converting the duration into seconds
    int tempDurationSeconds = (
          (Integer.parseInt(tempDuration[0]) * 3600)
        + (Integer.parseInt(tempDuration[1]) * 60)
    );
    //seting the duration
    return tempDurationSeconds;
  }



}
