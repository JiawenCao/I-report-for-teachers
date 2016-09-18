import java.util.ArrayList;

public class StudentInfo {
	private String studentName;
	private String studentLevel;
	private int currentGrade;
	private ArrayList assignmentGrade;
	
	/** create StudentInfo object
	 * 
	 * @param name	name of student 	
	 * @param level	grade level
	 * @param Grade		overall geade for student
	 */
	public StudentInfo(String name, String level, int Grade){
		studentName = name;
		studentLevel = level;
		currentGrade = Grade;
		assignmentGrade = new ArrayList();
	}

	/**
	 * the class that used to return the name of each student
	 * @return the name of each student
	 */
	public String getStudentName()
	{
		return studentName;
	}
	
	/**
	 * the class that used to return the level of each student
	 * @return the level of each student
	 */
	public String getStudentLevel()
	{
		return studentLevel;
	}

	/**
	 * the class that used to return the grade of each student
	 * @return the grade of each student
	 */
	public int getGrade()
	{
		return currentGrade;
	}
	
	/**
	 * the class that used to add value of array
	 */
	public void addGrade(double g)
	{
		assignmentGrade.add(g);
	}
	
	/**
	 * the class that used to return the grade of every assignment of the student
	 * @return the grade of every assignment of the student
	 */
	public ArrayList getAssignmentGrade()
	{
		return assignmentGrade;
	}
	
	/** 
	 * this is the method that is responsible for checking and dealing the fail student
	 * 
	 * @param if the student is failed 
	 */
	public boolean Fail()	{ return currentGrade<50;}
	
	
	/**
	 * we don't need it for now.................
	 * 
	 * this is the method that used to get the minimum grade of passing course
	 * 
	 * @param min: the minimum grade of passing course
	 
	public void line(int min)
	{
		line = min;
	}
	*/
	
}
