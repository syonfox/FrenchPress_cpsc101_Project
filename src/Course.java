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

	private double duration;

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
	public void setDuration(double t){
		this.duration = t;
	}
	public double getDuration(){
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
   public boolean isConflictWith(Course another){
		 @SuppressWarnings("deprecation")
		int ST1= startTime.getHours()*minsPerHour+startTime.getMinutes();
		int ST2= another.startTime.getHours()*minsPerHour+another.startTime.getMinutes();
		int ET1= ST1+getDuration();
		int ET2= ST2+another.getDuration();
		if(ST1<ST2 && ST2<ET1){
			return true;
		}
		else if (ST1>ST2 && ST1<ET2){
			return true;
		}
		
		return false;
		
	}
    public boolean equals(Course course){
    	if(getLocation().equals(course.getLocation()) && getProfessorName().equals(course.getProfessorName()))
    		{
    		return true;
    		}
    		return false;

    }

    public String toString(){
    	String days = "";
    	for(int i=0;i<daysOfWeek.size();i++)
    		days += daysOfWeek.get(i) + " ";
    	
    	return "CourseID = " + courseID + " ComponetID = " + componetID + " Course Date = " + date + " Days = " + days + " Start time = " + getStartTime() +
    	      " Duration = " + getDuration() + " Location = " + location + " Professor name = " + professorName;

    }
}
