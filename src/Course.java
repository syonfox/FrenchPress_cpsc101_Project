import java.util.ArrayList;

public class Course {
	private String courseID;
	private String componentID;
	private CourseDate dates;
	private CourseTime times;
	private ArrayList<String> daysOfWeek;
	private Location location;
	private String professorName;
	
	public Course(String courseID, String componentID, CourseDate dates, CourseTime times, ArrayList<String> daysOfWeek, Location location, String professorName){
		setCourseID(courseID);
		setComponentID(componentID);
		setDates(dates);
		setTimes(times);
		setDaysOfWeek(daysOfWeek);
		setLocation(location);
		setProfessorName(professorName);
	}
	
	public String toString(){
		return getCourseID() + " " + getComponentID() + " " + getDates() + " " + getTimes() + " " + getLocation() + " " + getProfessorName();
	}

	public String getCourseID() {
		return courseID;
	}

	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}

	public String getComponentID() {
		return componentID;
	}

	public void setComponentID(String componentID) {
		this.componentID = componentID;
	}

	public CourseDate getDates() {
		return dates;
	}

	public void setDates(CourseDate dates) {
		this.dates = dates;
	}

	public CourseTime getTimes() {
		return times;
	}

	public void setTimes(CourseTime times) {
		this.times = times;
	}

	public ArrayList<String> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(ArrayList<String> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getProfessorName() {
		return professorName;
	}

	public void setProfessorName(String professorName) {
		this.professorName = professorName;
	}
	
}