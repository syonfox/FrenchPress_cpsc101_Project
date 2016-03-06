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

    String inputFile = "testData.csv";
    DataReader lines = new DataReader(inputFile);
    lines.loadData();
    lines.printFile();
    // class[] = lines.toClassesArray();

  }
}
