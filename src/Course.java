/**
* @author Jiangtao Qiu
* @author Rodrigo Nicoletti
* @author Kier Lindsay
**/

import java.sql.Time;
import java.util.ArrayList;

public class Course {
	
	private String courseID;
	private String componetID;
	private String subject;
	private int level;
	private CourseDate date;
	private Time startTime;
	private Location location;
	private String professorName;
	private int duration;
	private boolean isForced=false;
	private ArrayList<String> daysOfWeek;
    
	
	/**
	 * Creates an empty course object
	 */
	public Course(){
		daysOfWeek = new ArrayList<String>();
	}

	/**
	 * @return ArrayList with days of the week
	 */
	public ArrayList<String> getDaysOfWeek(){
		return daysOfWeek;
	}
	
	/**
	 * Sets days of the week
	 * @param daysOfWeek
	 */
	public void setDaysOfWeek(ArrayList<String> daysOfWeek){
		this.daysOfWeek =daysOfWeek;
	}
	
	/**
	 * Adds a day of the week to the course
	 * @param day
	 */
	public void addDayOfWeek(String day){
		daysOfWeek.add(day);
	}
	
	/**
	 * @return start and end dates of the course
	 */
	public CourseDate getDates(){
		return date;
	}
	
	/**
	 * Sets dates of the course
	 * @param dates
	 */
	public void setDates(CourseDate dates){
		this.date=dates;
	}
	
	/**
	 * @return start time
	 */
	public Time getStartTime(){
		return startTime;
	}
	
	/**
	 * Sets start time
	 * @param start time
	 */
	public void setStartTime(Time t){
		this.startTime = t;
	}
	
	/**
	 * Sets duration of the course
	 * @param t
	 */
	public void setDuration(int t){
		this.duration = t;
	}
	
	/**
	 * @return duration of the course
	 */
	public int getDuration(){
		return this.duration;
	}

	/**
	 * @return course ID
	 */
	public String getCouseID(){
		return this.courseID;
	}
	
	/**
	 * Sets course ID
	 * @param courseId
	 */
	public void setCourseID(String courseId){
		this. courseID=courseId;
	}

	/**
	 * @return component ID
	 */
	public String getComponetID(){
		return this.componetID;
	}
	/**
	 * Sets component ID
	 * @param componetID
	 */
	public void setComponetID(String componetID){
		this. componetID= componetID;
	}

	/**
	 * @return location
	 */
	public Location getLocation(){
		return location;
	}
	
	/**
	 * Sets location of the course
	 * @param location
	 */
	public void setLocation(Location location){
		this.location=location;
	}

	/**
	 * @return professor name
	 */
	public String getProfessorName(){
		return this.professorName;
	}
	
	/**
	 * Sets professor name
	 * @param professor name
	 */
	public void setProfessorName(String p){
		this.professorName=p;
	}

   	/**
   	 * @param isforced
   	 */
   	public void setIsForced(boolean isforced){
   		this.isForced= isforced;
   	}
   	
 	/**
 	 * @return true of the course is forced, false if it is not
 	 */
 	public boolean getIsForced(){
	  return true;
   	}
 	
   	/**
   	 * Sets the subject name
   	 * @param s
   	 */
   	public void setSubject(String s){
	   this.subject = s;
	}
   	
   	/**
   	 * @return subject name
   	 */
   	public String getSubject(){
   		return subject;
   	}
   	
   	/**
   	 * Sets the level of the course
   	 * @param course level
   	 */
   	public void setLevel(int l){
   		this.level = l;
   	}
   	
   	/**
   	 * @return course level
   	 */
   	public int getLevel(){
   		return level;
   	}
   	
	/**
	 * Compare 2 courses to check if they happen on the same days of the week.
	 * @param other course
	 * @return true if they happen on the same day, false if they do not
	 */
	public boolean compareDays(Course other){
		for(int i = 0; i < daysOfWeek.size(); i++){
	    for(int j = 0; j < other.getDaysOfWeek().size(); j++){
	      if(daysOfWeek.get(i).equals(other.getDaysOfWeek().get(j)))
	        return true;
	      }
	    }
	    return false;
	  }

	 /**
	  * Checks if 2 courses happen at the same time.
	  * @param other course object
	  * @return true if they happen at the same time, false if they do not.
	  */
	@SuppressWarnings("deprecation")
	public boolean conflictsWith(Course another){
		if(compareDays(another)) {
			int sT1=  startTime.getHours()*60+startTime.getMinutes();
			int sT2= another.startTime.getHours()*60+another.startTime.getMinutes();
			int eT1= sT1+getDuration();
			int eT2= sT2+another.getDuration();
			 
			 if(sT1 == sT2){
				return true;
			 }
			 
			 if(eT1 == eT2){
				return true;
			 }
			 
			 if(sT1<sT2 && sT2<eT1){
				return true;
			 }
			 else if (sT1>sT2 && sT1<eT2){
				return true;
			 }
			 
			 return false;
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