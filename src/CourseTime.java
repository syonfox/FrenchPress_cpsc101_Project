import java.sql.Time;

public class CourseTime {
	Time startTime;
	Time endTime;
	
	public CourseTime(Time start, Time end){
		this.startTime = start;
		this.endTime = end;
	}
	
	public boolean equals(CourseTime other){
		if(getStartTime().equals(other.getStartTime()) && getEndTime().equals(other.getEndTime()))
			return true;
		return false;
	}
	
	public boolean conflicts(CourseTime other){
		if(this.equals(other))
			return true;
		if(getStartTime().compareTo(other.getEndTime()) == 1)
			return false;
		if(getEndTime().compareTo(other.getEndTime()) == -1)
			return false;
		return true;
	}
	
	public Time getStartTime(){
		return startTime;
	}
	
	public Time getEndTime(){
		return endTime;
	}
}