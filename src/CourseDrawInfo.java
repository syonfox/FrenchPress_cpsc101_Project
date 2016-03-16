/**This will have the info TimeTablePanleNeeds to draw a course
*@author Kier Lindsay
*@date 2016-03
*/
import java.awt.Color;

public class CourseDrawInfo {

  /**
   * The color of the course when it gets drawen
   */
  private Color color;
  //private int numOfDays;
  /**
   * An array of the days (number 0 = mon ..6 = sun)
   */
  private int[] days;

  /**
   * The interger for the start time of the course
   */
  private int startTime;

  /**
   * the number of 30min blockes the course gose for
   */
  private int duration;

  /**
   * What should be displayed on the course
   */
  private String displayString;

  /**
   * The constructior
   * @param conflict wether or not this corse conflicts with anything
   * @param idays the array of days
   * @param st the start time
   * @param iduration the duration
   * @param ds the display string
   */
  public CourseDrawInfo(boolean conflict, int[] idays, int st, int iduration, String ds){
    if(conflict) color = new Color(225, 102, 102, 200);
    else color = new Color(102, 225, 102, 200);

    days = idays;
    startTime = st;
    duration = iduration;
    displayString = ds;

  }

  /**
   * method to get the color
   * @return the color of the course
   */
  public Color getColor() {
    return color;
  }

  /**
   * method to get the number of days in the course
   * @return the length of days
   */
  public int getNumberOfDays() {
    return days.length;
  }

  /**
   * returned the day for an index
   * @return the day for index i
   */
  public int getDay(int i){
    return days[i];
  }

  /**
   * method to get the Start Time
   * @return the StartTime of the course
   */
  public int getStartTime() {
    return startTime;
  }
/**
   * method to get the duration
   * @return the duration of the course
   */
  public int getDuration() {
    return duration;
  }
  /**
   * method to get the Display string
   * @return the display string of the course
   */
  public String getDisplayString() {
    return displayString;
  }
}
