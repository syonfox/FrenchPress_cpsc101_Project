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

	private int duration;

	private boolean isForced=false;

	private ArrayList<String> daysOfWeek;

	public Course(){
		daysOfWeek = new ArrayList<String>();
	}

	public ArrayList<String> getDaysOfWeek(){
          	return daysOfWeek;
	}
	public void setDaysOfWeek(ArrayList<String> daysOfWeek){
		this.daysOfWeek =daysOfWeek;
	}
	public void addDayOfWeek(String day){
		daysOfWeek.add(day);
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
	public void setDuration(int t){
		this.duration = t;
	}
	public int getDuration(){
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
			String stringToReturn = "CourseID = " + courseID + " ComponetID = " + componetID + " Course Date = " + date + " Start time = " + getStartTime() +
    	      " Duration = " + getDuration() + " Location = " + location + " Professor name = " + professorName + " Days Of The Week = ";
			for(int i=0; i<daysOfWeek.size()-1; i++) {
				stringToReturn += daysOfWeek.get(i) + ", ";
			}
			stringToReturn += daysOfWeek.get(daysOfWeek.size()-1);
    	return stringToReturn;

  }
}
