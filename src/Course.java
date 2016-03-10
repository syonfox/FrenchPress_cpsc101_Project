//package cpsc101project;
import java.sql.Time;
import java.util.ArrayList;
public class Course {

	private String courseID;

	private String componetID;
	
	private CourseDate date;
	private Time startTime;

	private Location location;
	private String professorName;

	private Time duration;

	private boolean isForced=false;

	private ArrayList<String> daysOfWeek;
	
	public ArrayList<String> getDaysOfWeek(){
          	return daysOfWeek;
	}
	public void setDaysOfWeek(ArrayList<String> daysOfWeek){
		this.daysOfWeek =daysOfWeek;
	}
	public CourseDate getDates(){
		return date;
	}
	public void setDates(CourseDate dates){
		this.date=dates;
	}
	public Time getStartTime(){
		return startTime;
	}
	public void setStartTime(Time t){
		this.startTime = t;
	}
	public void setDuration(Time t){
		this.duration = t;
	}
	public Time getDuration(){
		return this.duration;
	}
	
	public String getCouseID(){
		return this.courseID;
	}
	public void setCourseID(String courseId){
		this. courseID=courseId;
	}

	public String getComponetID(){
		return this.componetID;
	}
	public void setComponetID(String componetID){
		this. componetID= componetID;
	}

	public Location getLocation(){
		return location;
	}
	public void setLocation(Location location){
		this.location=location;
	}

	public String getProfessorName(){
		return this.professorName;
	}
	public void setProfessorName(String p){
		this.professorName=p;
	}
        public void setIsForced(boolean isforced){
        	this.isForced= isforced;
        }
        public boolean getIsForced(){
          return true;	
        }
    public boolean equals(Course course){
    	if(getLocation().equals(course.getLocation()) && getProfessorName().equals(course.getProfessorName()))
    		{
    		return true;
    		}
    		return false;

    }

    public String toString(){

    	return "CourseID = " + courseID + " ComponetID = " + componetID + " Course Date = " + date + " Start time = " + getStartTime() +
    	      " Duration = " + getDuration() + " Location = " + location + " Professor name = " + professorName;

    			}
}
