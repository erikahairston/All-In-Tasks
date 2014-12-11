package sra.com;

public class MemberTasks {

	public static void main(String[] args) 
	{
		///I need to make an onClick method that changes boolean newTask to true
		boolean newTask= true; //when user clicks on "New Task" button, this boolean becomes true and makes a new input line for user
		String userInputNameofAttendee = "hi";
		String userInputTask = "do this";
		int countAttendees = 0;
		
		for (int i= 0; i < countAttendees; i++)
		{
			System.out.print("Member Name: " + userInputNameofAttendee);
			//prints out what was inputed under Members method
			while (newTask)
			{
				System.out.print("Task 1: (checkbox)" + userInputTask);
				//goes into scanner userInputTask is a place where the user types in the task
				//System.out.println(" " + userInputTaskDueDate);
			}
			
		}
	}
}
	
