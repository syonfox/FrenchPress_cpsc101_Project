package cpsc101project;
import java.util.ArrayList;
public class Course {
	
	private String courseID;

	private String componetId;
	private CourseDate date;
	private CourseTime time;
	
	private Location location;
	private String professorName;
	

	private boolean isForced=false;
	
	private ArrayList<String> daysOfWeek;
	public Course(){
	
	}
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
	public CourseTime getTimes(){
		return time;
	}
	public void setTimes(CourseTime times){
		this.time = times;
	}
	public String getCouseID(){
		return this.courseID;
	}
	public void setCourseID(String courseId){
		this. courseID=courseId;
	}

	public String getComponetId(){
		return this.componetId;
	}
	public void setComponetId(String componetId){
		this. componetId= componetId;
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
   
    public boolean equals(Course course){
    	if(getLocation().equals(course.getLocation()) && getProfessorName().equals(course.getProfessorName()))
    		{
    		return true;
    		}
    		return false;
    	
    	
    	
    }
   
    public String toString(){
    	return "CourseID= "+courseID+", componetID"+componetId+", Course Date"+ date+", Course Time"+time+   
    	      ", location"+location+", professor name"+professorName;
    			}
    
    
    
}
