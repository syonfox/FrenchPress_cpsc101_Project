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



class Data {
  /** file is a file object that links to the file's path **/
  private File file;
  /** that ArrayList filelines is uses to store a file within the fileManager **/
  private ArrayList<String[]> fileLines;
  
  /** Constuctor takes a file path as a string and makes the file, it also initalizes fileList 
  *@param filePath the path to the data File
  */
  public Data(String filePath) {
  
    file = new File(filePath);
    

    fileLines = new ArrayList<String[]>();
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
	  System.out.println(line);
	  
	  //lineData = line.split(",");
	  
	 // System.out.println(lineData[0]+lineData[1]+lineData[2]+lineData[3]+lineData[4]+lineData[5]+lineData[6]+lineData[7]+lineData[8]+lineData[9]+lineData[10]+lineData[11]+lineData[12]);
	  if( !lineData[4].equals("") ||  !lineData[7].equals("") ) {
	    fileLines.add(lineData);
	  }
	  line = fileIn.readLine();
	}
	
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

  
  
  /*We dont need this for what we are curently doing but ill leave it in incase we need to write the data back to file.
  /** Appends a string to the end of the ArrayList that represents a file 
  * @param stringToAppend the string we want to append.
  
  public void append(String stringToAppend) {
    fileLines.add(stringToAppend);
  }
  */
  /* Writes a file from its ArrayList, this must be run for a file to be changed or created **/
  /*public void writeToFile() {
    try {
    
      file.delete();
      file.createNewFile();
      BufferedWriter fileOut = new BufferedWriter(new FileWriter(file));
      
      for(int i = 0; i < fileLines.size(); i++) {
	fileOut.write(fileLines.get(i));
	fileOut.newLine();
      }
      fileOut.close();
    } catch(IOException e) {
      System.out.println("Error: IO Exeption");
    }
  }
  */
  

}