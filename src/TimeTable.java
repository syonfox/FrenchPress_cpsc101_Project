/** The Schedule class is a class meant to interact with both courses and the graphical interface. 

import com.sun.org.apache.xpath.internal.operations.String;
import java.sql.Time;
import java.util.ArrayList;
*It will take in a list of courses selected through the graphical interface by the user. 
*After obtaining a list of copurses it will be able to check for any conflicts and output information in a format ready to be displayed by the graphical interface.
*
*
**/
import java.util.ArrayList;
import java.sql.Time;

public class TimeTable {

  private String timeTableName;
  private ArrayList<Course> courses;
  private ArrayList<CourseDrawInfo> courseDI;
  //private int numberOfCourses;
  private ArrayList<String> conficts;
  private boolean[] hasConflict;


  public TimeTable(String name) {
    timeTableName = name;
    courses = new ArrayList<Course>();
    //int numberOfCourses = 0;
  }
  public TimeTable(String name , ArrayList<Course> initalCourses) {
    timeTableName = name;
    courses = initalCourses;

    for(int i = 0; i < courses.size(); i++){
    //  System.out.println(courses.get(i).toString());
    }
    //int numberOfCourses = cou;
  }

  public String[] getConflicts() {
    return conficts.toArray(new String[conficts.size()]);
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

  public void cheackConfilcts() {
    conficts = new ArrayList<String>();
    hasConflict = new boolean[courses.size()];
    String tempConfilcts;
    boolean comma;
    for(int i = 0; i < courses.size(); i++) {
      hasConflict[i] = false;
      comma = false;
      tempConfilcts = "";
      for(int j = 0; j < courses.size(); j++) {
        if(i!=j && courses.get(i).conflictsWith(courses.get(j))) {
          hasConflict[i] = true;
          if(comma) tempConfilcts+=", ";
          comma = true;
          tempConfilcts += courses.get(j).getCouseID()+"-"+courses.get(j).getComponetID();
        }
      }
      if(hasConflict[i]) {
        conficts.add(courses.get(i).getCouseID()+"-"+courses.get(i).getComponetID()
                      + " Conflicts with: " + tempConfilcts + "\n");
      }
    }
  }

  @SuppressWarnings("deprecation")
  public void prepareCourseDrawInfo(){
    courseDI = new ArrayList<CourseDrawInfo>();
    Course tempC;
    CourseDrawInfo tempCDI;
    int[] days;
    ArrayList<String> dow;
    Time st;
    int intst;
    int duration;
    String displayString;
    for(int i = 0; i < courses.size(); i++) {
      tempC = courses.get(i);
      dow = tempC.getDaysOfWeek();
      days = new int[dow.size()];
      for(int j=0; j < dow.size(); j++) {
        switch (dow.get(j)) {
          case "Monday":
            days[j]=0;
            break;
          case "Tuesday":
            days[j]=1;
            break;
          case "Wednesday":
            days[j]=2;
            break;
          case "Thursday":
            days[j]=3;
            break;
          case "Friday":
            days[j]=4;
            break;
        }
      }
      st = tempC.getStartTime();
      intst = (st.getHours()*2 + st.getMinutes()/30) - 16;
      duration = tempC.getDuration()/30;
      displayString = tempC.getCouseID() +"-"+tempC.getComponetID()+" "
                      + tempC.getLocation().getRoomNumber();
      tempCDI = new CourseDrawInfo(hasConflict[i], days, intst, duration, displayString);
      courseDI.add(tempCDI);
    }

  }
  public boolean hasNext(int i) {
    //System.out.println("hasNext:"+i+"size:"+courseDI.size()+(i < courseDI.size()));
    return (i < courseDI.size());
  }
  public CourseDrawInfo getCourseDrawInfo(int i) {
    return courseDI.get(i);
  }
}
