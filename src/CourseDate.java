/**
* @author Jiangtao Qiu
* @author Rodrigo Nicoletti
* @author Kier Lindsay
**/

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
	
   /**
	 * @return start date
	 */
	public Date getStartDate(){
		return startDate;
	}
	
   /**
	 * @return end date
	 */
	public Date getEndDate(){
		return endDate;
	}
	
	public String toString(){
		return "From " + getStartDate() + " to " + getEndDate();
	}
}