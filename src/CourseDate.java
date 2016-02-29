public class CourseDate {

	Date startDate;
	Date endDate;
	
	public CourseDate(Date start, Date end){
		startDate = start;
		endDate = end;
	}
	
	public boolean equals(CourseDate other){
		if(startDate.equals(other.startDate) && endDate.equals(other.endDate))
			return true;
		return false;
	}
	
	public Date getStartDate(){
		return startDate;
	}
	
	public Date getEndDate(){
		return endDate;
	}
}
