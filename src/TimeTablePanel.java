/**This is a panle desigend to be placed in the gui and contain a TimeTable
 *@author Kier Lindsay
 *
 */

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

@SuppressWarnings("serial")
public class TimeTablePanel extends JPanel {
    //private ArrayList<Course> courses;
    private TimeTable timeTable;
  public void draw(Graphics g) {
    //g.fillRect(0,0,getSize().getWidth(),getSize(.getHeight()));
    //some mystical constants based on the width on the dimensions
    double calTemp;//Temporary value for calculationg values
    int height = (int) getSize().getHeight();//height of the TimeTable Pane
    int width = (int) getSize().getWidth();//width of the time table pane
    int timeW = 100;  //The width of the Time box (on the left)
    calTemp = (height-40)/28;
    int timeH = (int) calTemp;  //The height of the Time box (on the left)
    calTemp = (width-100)/7;
    int dayW = (int) calTemp; //The Width of the Day Box (top)
    int dayH = 40; //The Hight of the Day boxes (top)

    //the days of the week;
    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday",
                     "Saturday", "Sunday"};
    //all of the times to display
    String[] times = {" 8:00am", " 8:30am", " 9:00am", " 9:30am", "10:00am", "10:30am",
                      "11:00am", "11:30am", "12:00am", "12:30am", " 1:00pm",
                      " 1:30pm", " 2:00pm", " 2:30pm", " 3:00pm", " 3:30pm",
                      " 4:00pm", " 4:30pm", " 5:00pm", " 5:30pm", " 6:00pm",
                      " 6:30pm", " 7:00pm", " 7:30pm", " 8:00pm", " 8:30pm",
                      " 9:00pm"," 9:30pm"
                      };




    Graphics2D g2d = (Graphics2D) g;

    //fonts
    Font deafultFont = new Font ("Terminal", 1, 12);
    Font bigFont = new Font ("Terminal", 1, 16);
    Color evenColorTime = new Color(164,164,164);
    Color oddColorTime  = new Color(142,142,142);
    Color evenColorDay = new Color(164,164,164, 128);
    Color oddColorDay  = new Color(142,142,142, 128);
    //draw the black box in the upper left
    g2d.setColor(Color.BLACK);
    g2d.fillRect(0,0,timeW,dayH);
    setBackground(oddColorTime);

    //draw the times down the side
    g2d.setFont(deafultFont);
    boolean isEvenLine = false;

    for(int i = 0, x = 0, y = dayH; i < times.length; i++) {

      if(isEvenLine) {
        g2d.setColor(evenColorTime);
        isEvenLine = false;
        g2d.fillRect(x, y, width, timeH);

      } else {
        g2d.setColor(oddColorTime);
        isEvenLine = true;
        g2d.fillRect(x, y, width, timeH);
      }
      g2d.setColor(Color.BLACK);
      g2d.drawRect(x, y, timeW, timeH);
      g2d.drawString(times[i], x+10, y+15);
      y += timeH;
    }

    //draw the days of the week across the top;
    g2d.setFont(bigFont);
    isEvenLine = false;
    for(int i = 0, x = timeW, y = 0; i <days.length; i++) {

      if(isEvenLine) {
        g2d.setColor(evenColorDay);
        isEvenLine = false;
        g2d.fillRect(x, y, dayW, height);

      } else {
        g2d.setColor(oddColorDay);
        isEvenLine = true;
        g2d.fillRect(x, y, dayW, height);
      }

      g2d.setColor(Color.BLACK);
      g2d.drawRect(x, y, dayW, dayH);
      g2d.drawString(days[i], x+10, y+30);
      x += dayW;
    }



    CourseDrawInfo cdi; //place to stro the courseDrawInfo
    int index = 0; // We start at 0
    g2d.setFont(deafultFont);
    //we only try to draw if we have stuff to draw
    if(timeTable != null) {
      //thie gose to the timetable and asks if there is an itam at index
      while(timeTable.hasNext(index)) {
        //gets the course draw info from the  timetable at index and sets it to cdi
        cdi = timeTable.getCourseDrawInfo(index);
        //for every tay in the number of days that the couse happens
        for(int i = 0; i < cdi.getNumberOfDays(); i++) {
            g2d.setColor(cdi.getColor());
            //Fills in a rectangle that takes up the space on the timetable that the course uses
            g2d.fillRect(timeW+(dayW*cdi.getDay(i)), dayH+(timeH*cdi.getStartTime()) ,dayW ,timeH*cdi.getDuration() );
            g2d.setColor(Color.BLACK);
            //makes a black borader around the rectangle
            g2d.drawRect(timeW+(dayW*cdi.getDay(i)), dayH+(timeH*cdi.getStartTime()) ,dayW ,timeH*cdi.getDuration() );
            //Draws the String for the cours ontop of the rectangle
            g2d.drawString(cdi.getDisplayString().split(" ")[0], timeW+(dayW*cdi.getDay(i))+10, dayH+(timeH*cdi.getStartTime()+15) );
            g2d.drawString(cdi.getDisplayString().split(" ")[1], timeW+(dayW*cdi.getDay(i))+10, dayH+timeH+(timeH*cdi.getStartTime()+15) );
        }
        //advance to the next course
        index++;
      }
    }
  }

  /**
   * Allows you to set the timeTable you want to display
   * @param tt the TimeTable
   */
  public void setTimeTable(TimeTable tt) {
    timeTable = tt;
  }

  /**
   * Let it Paint you a picture
   * @param g A graphics
   */
  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }
}
