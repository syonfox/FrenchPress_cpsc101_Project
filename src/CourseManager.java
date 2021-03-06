/**
* @author Jiangtao Qiu
* @author Rodrigo Nicoletti
* @author Kier Lindsay
**/

import java.util.ArrayList;

public class CourseManager {

	ArrayList<Course> courses;

	public CourseManager(ArrayList<Course> c){
		this.courses = c;
	}

   /**
	 * Turns an ArrayList of Strings into an ArrayList of Courses
	 * @param array list containing the strings
         * @return array list with the courses
	 */
	public ArrayList<Course> toCourseArrayList(ArrayList<String> s){
		ArrayList<Course> selectedCourses = new ArrayList<Course>();
		String courseIDAndSection = "";
		for(int i = 0; i < s.size(); i++){
			for(int j = 0; j < courses.size(); j++){
				courseIDAndSection = courses.get(j).getCouseID() + "-" + courses.get(j).getComponetID();
				if(courseIDAndSection.substring(0, 10).equals(s.get(i).substring(0, 10)))
					selectedCourses.add(courses.get(j));
			}
			
		}
		return selectedCourses;
	}

   /**
	 * get information about a course.
	 * @param subject name
         * @return all the courses that match the string s
	 */
	public ArrayList<String> getSubjectCoursesArrayList(String s){
		ArrayList<String> result = new ArrayList<String>();

		for(int i = 0; i < courses.size(); i++){
			if(courses.get(i).getCouseID().substring(0, 4).equals(s))
				result.add(courses.get(i).getCouseID() + "-" + courses.get(i).getComponetID() + " " + courses.get(i).getLocation().getRoomNumber() + " " + courses.get(i).getProfessorName());
		}

		return result;
	}
}
