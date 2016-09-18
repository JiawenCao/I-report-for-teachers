import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.util.ArrayList;

public class readFile {
	
	public static ArrayList<AssignmentInfo> assignments = new ArrayList<AssignmentInfo>();
	public static ArrayList<StudentInfo> students = new ArrayList<StudentInfo>();	
	
	
	/**
	 * 
	 * @param fileName 	the name and position of the file that we are going to read
	 * @throws Exception		throw Exception
	 */
	public static void read(String fileName) throws Exception{
		File file = new File(fileName);
		BufferedReader input = new BufferedReader(new FileReader(file));
		students = new ArrayList<StudentInfo>();
		assignments = new ArrayList<AssignmentInfo>();
		
		//assignment in object
		String s = input.readLine();
		
		//read the title(course name)
		int bracket = s.indexOf(")");
		int comma = s.indexOf(",", bracket);//index of comma check from bracket
		String couseName = s.substring(bracket+2, comma);
		Populater.Course(couseName); // fill the course name of I- Report	
		
		//System.out.println(couseName); 		// uncomment to test
		
		//read the assignments
		s = input.readLine();
		
		do{
		// find the assignment name
		int quotes = s.indexOf(",\"");
		String name = s.substring(quotes+2);
		//System.out.println(name);		// uncomment to test
		
		// find the assignment total
		s = input.readLine();//date
		s = input.readLine();
		int space = s.indexOf(" ");
		String total = s.substring(space+1);
		//System.out.println(total);		// uncomment to test
		
		// create assignment
		AssignmentInfo a = new AssignmentInfo(name, Integer.parseInt(total));
		
		// put assignment into our list of assignments
		assignments.add(a);
		
		s = input.readLine();
	}while (s.charAt(0) == ' ' && s.charAt(s.length()-1) != '\"');
		
		
		
		//student total 
		s = input.readLine();
		do{
			String[] all = s.split(",");
			//int symbol = s.indexOf(",");
			int space1 = all[1].indexOf(" ");
			int percent = all[1].indexOf("%");
			
			
			ArrayList<StudentInfo> assignmentPart = new ArrayList<StudentInfo>();
			
			//students' name
			String studentName = all[0];
			//students' level
			String level = all[1].substring(0,space1);
			//students' percentage
			String percentage = all[1].substring(space1+1, percent);
			
			// only for test
			//System.out.println(studentName);
			//System.out.println(level);
			//System.out.println(percentage);
			
			StudentInfo b = new StudentInfo(studentName,level,Integer.parseInt(percentage));
			//student's each assignment mark
			//do{
				
				for (int i =2; i < all.length; i++)
				{
					int assignmentScore = -1;  // -1 for missing
					try 
					{
						assignmentScore = Integer.parseInt(all[i]);
					} catch (Exception e) 
					{}
					
					//System.out.println(assignmentScore);		// uncomment for test
					b.addGrade(assignmentScore);
				}
				
				
			//}while (s.charAt(start-1) == ',');

			students.add(b);
		s= input.readLine();
		}while(s != null);

		input.close();

	}



}
