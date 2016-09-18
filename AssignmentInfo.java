public class AssignmentInfo 
{
		private  String assignmentName;
		private  int assignmentTotal;
		/**
		 * 
		 * @param name name of assignment
		 * @param mark total mark 
		 */
		public AssignmentInfo(String name, int mark)
		{
			assignmentName = name;
			assignmentTotal = mark;
		}
		
		/**
		 * this method is used to get the name of each assignment
		 * @return name of assignment
		 */
		public String getassignmentName(){
			return assignmentName;
		}
		
		/**
		 * this method is used to get the total mark of assignment
		 * @return total mark of assignment
		 */
		public double getTotalMark(){
			return assignmentTotal;
		}
		
		/** Only for test
		
		public void main(String[] arg)
		{
			System.out.println(getassignmentName());
			System.out.println(getTotalMark());
		}
		
		*/


		

}