import java.util.ArrayList;

public class CourseManager {
	
	ArrayList<Course> courses;
	
	public CourseManager(ArrayList<Course> c){
		this.courses = c;
	}
	
	public ArrayList<Course> toCourseArrayList(ArrayList<String> s){
		ArrayList<Course> selectedCourses = new ArrayList<Course>();
		
		for(int i = 0; i < s.size(); i++){
			for(int j = 0; j < courses.size(); j++){
				if(courses.get(j).getCouseID().equals(s.get(i).substring(0, 7)))
					selectedCourses.add(courses.get(j));
			}
		}
		return selectedCourses;
	}
	
	public ArrayList<String> getSubjectCoursesArrayList(String s){
		ArrayList<String> result = new ArrayList<String>();
		
		for(int i = 0; i < courses.size(); i++){
			if(courses.get(i).getCouseID().substring(0, 4).equals(s))
				result.add(courses.get(i).getCouseID() + "-" + courses.get(i).getComponetID() + " " + courses.get(i).getLocation().getRoomNumber() + " " + courses.get(i).getProfessorName());
		}
		
		return result;
	}
}
