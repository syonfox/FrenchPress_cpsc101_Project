/**This is a panle desigend to be placed in the gui and contain a TimeTable**/

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
    double calTemp;
    int height = (int) getSize().getHeight();
    int width = (int) getSize().getWidth();
    int timeW = 100;
    calTemp = (height-40)/26;
    int timeH = (int) calTemp;
    calTemp = (width-100)/5;
    int dayW = (int) calTemp;
    int dayH = 40;

    String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] times = {" 8:00am", " 8:30am", " 9:00am", " 9:30am", "10:00am", "10:30am",
                      "11:00am", "11:30am", "12:00am", "12:30am", " 1:00pm",
                      " 1:30pm", " 2:00pm", " 2:30pm", " 3:00pm", " 3:30pm",
                      " 4:00pm", " 4:30pm", " 5:00pm", " 5:30pm", " 6:00pm",
                      " 6:30pm", " 7:00pm", " 7:30pm", " 8:00pm", " 8:30pm",
                      };

    setBackground(Color.YELLOW);

    Graphics2D g2d = (Graphics2D) g;

    Font deafultFont = new Font ("Terminal", 1, 12);
    Font bigFont = new Font ("Terminal", 1, 16);

    g2d.setColor(Color.BLACK);
    g2d.fillRect(0,0,timeW,dayH);
    //
    g2d.setFont(bigFont);
    for(int i = 0, x = timeW, y = 0; i <5; i++) {
      g2d.drawRect(x, y, dayW, dayH);
      g2d.drawString(days[i], x+10, y+30);
      x += dayW;
    }

    g2d.setFont(deafultFont);
    for(int i = 0, x = 0, y = dayH; i < times.length; i++) {
      g2d.drawRect(x, y, timeW, timeH);
      g2d.drawString(times[i], x+10, y+15);
      y += timeH;
    }
    CourseDrawInfo cdi;
    int index = 0;
    if(timeTable != null) {
      while(timeTable.hasNext(index)) {

        cdi = timeTable.getCourseDrawInfo(index);
        g2d.setColor(cdi.getColor());
        //System.out.println("Drawing CDI :"+index + "st:" +cdi.getStartTime()+"Color:"+cdi.getColor().toString());
        for(int i = 0; i < cdi.getNumberOfDays(); i++) {
            g2d.setColor(cdi.getColor());

            //System.out.println(cdi.getDisplayString() +" duration:"+cdi.getDuration()+" height:"+timeH*cdi.getDuration());

            g2d.fillRect(timeW+(dayW*cdi.getDay(i)), dayH+(timeH*cdi.getStartTime()) ,dayW ,timeH*cdi.getDuration() );
            g2d.setColor(Color.BLACK);
            g2d.drawRect(timeW+(dayW*cdi.getDay(i)), dayH+(timeH*cdi.getStartTime()) ,dayW ,timeH*cdi.getDuration() );
            g2d.drawString(cdi.getDisplayString(), timeW+(dayW*cdi.getDay(i))+10, dayH+(timeH*cdi.getStartTime()+15) );
        }
        index++;
        //g2d.fillRect(,0,timeW,dayH);
      }
    }
    //System.out.println("width: "+getSize().getWidth() +"  height: "+ getSize().getHeight());
  }

  public void setTimeTable(TimeTable tt) {
    timeTable = tt;
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    draw(g);
  }
}
