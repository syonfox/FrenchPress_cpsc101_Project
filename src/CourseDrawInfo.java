/**This will have the info TimeTablePanleNeeds to draw a course
*@author Kier Lindsay
*@date 2016-03
*/
import java.awt.Color;

public class CourseDrawInfo {

  private Color color;
  //private int numOfDays;
  private int[] days;
  private int startTime;
  private int duration;
  private String displayString;

  public CourseDrawInfo(boolean conflict, int[] idays, int st, int iduration, String ds){
    if(conflict) color = new Color(225, 102, 102, 200);
    else color = new Color(102, 225, 102, 200);

    days = idays;
    startTime = st;
    duration = iduration;
    displayString = ds;

  }

  public Color getColor() {
    return color;
  }

  public int getNumberOfDays() {
    return days.length;
  }

  public int getDay(int i){
    return days[i];
  }

  public int getStartTime() {
    return startTime;
  }

  public int getDuration() {
    return duration;
  }

  public String getDisplayString() {
    return displayString;
  }
}
