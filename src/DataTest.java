import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;

/** Main takes no arguments but must have file names set inside of it **/
public class DataTest {

  public static void main(String[] args) {

    File inputFile = new File("testData.csv");
    DataReader lines = new DataReader(inputFile);
    lines.loadData();
    //lines.printFile();
    lines.makeCourseArray();
    ArrayList<Course> cs = lines.getCourseArrayList();

    for(int i = 0; i < cs.size(); i++) {
      System.out.println(cs.get(i).toString());

    }

  }
}
