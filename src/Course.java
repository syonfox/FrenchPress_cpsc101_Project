package cpsc101project;

public class Course {
	
	private String courseID;
	//private String courseName;
	private String componetId;
	private String startDate;
	private String endDate;
	private String location;
	private String professorName;
	
	private int timeStart;
	private int Day;
	private int Duration;
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
	public String getCouseID(){
		return this.courseID;
	}
	public void setCourseID(String courseId){
		this. courseID=courseId;
	}
	/** public String courseName(){
		 return this.courseName;
	 }
	public void setCourseName(String courseName){
		this. courseName=courseName;
	}
	*/
	public String getComponetId(){
		return this.componetId;
	}
	public void setComponetId(String componetId){
		this. componetId= componetId;
	}
	public String getStartDate(){
		return this.startDate;
	}
	public void setStartDate(String startDate){
		this. startDate= startDate;
	}
	public String getEndDate(){
		return this.endDate;
	}
	
	public void setEndDate(String ed){
		this.endDate = ed;
	}
	
	public String getLocation(){
		return this.location;
	}
	public void setLocation(String location){
		this.location=location;
	}
	
	public String getProfessorName(){
		return this.professorName;
	}
	public void setProfessorName(String p){
		this.professorName=p;
	}
    public int getTimeStart(){
    	return this.timeStart;
    }
    public void setTimeStart(int TS){
    	this.timeStart=TS;
    }
    public int getDay(){
    	return this.Day;
    }
    public void setDay(int day){
    	this.Day=day;
    }
    public int getDuration(){
    	return this.Duration;
    }
    public void setDuration(int d){
    	this.Duration=d;
    }
    public boolean equals(Course course){
    	if(getLocation().equals(course.getLocation()) && getProfessorName().equals(course.getProfessorName()))
    		{
    		return true;
    		}
    		return false;
    	
    	
    	
    }
   
    public String toString(){
    	return "CourseID= "+courseID+", componetID"+componetId+", startDate"  
    			+startDate+", endDate"+endDate+",location"+location+", professor name"
    			+professorName+",time to start"+timeStart+", Day"+Day+", Duration"
    			+Duration;
    			}
    
    
    
}
