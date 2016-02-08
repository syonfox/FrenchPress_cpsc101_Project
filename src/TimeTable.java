/** The Schedule class is a class meant to interact with both courses and the graphical interface. It will take in a list of courses selected through the graphical interface by the user. After obtaining a list of copurses it will be able to check for any conflicts and output information in a format ready to be displayed by the graphical interface.
*
*
**/

import java.util.ArrayList;

public class TimeTable {

  private String timeTableName;
  private ArrayList<Course> courses;
  //private int numberOfCourses;
  
  
  public TimeTable(String name) {
    timeTableName = name;
    courses = new ArrayList<Course>;
    //int numberOfCourses = 0;
  }
  public TimeTable(String name , ArrayList<Course> initalCourses) {
    timeTableName = name;
    courses = initalCourses;
    //int numberOfCourses = cou;
  }
  
  public void add(Course courseToAdd) {
    //may want to cheack if the course is already in the list.
    courses.add(courseToAdd);
  }
  
  public void del(Course courseToDel) {
    for(int i = 0; i < courses.size(); i++) {
      if(courses.get(i).equals(courseToDel)) {
	courses.remove(i);
	break; // may want to make it cheack all courses in the future but there shouldent be duplacets.
      }
    }
  }

}