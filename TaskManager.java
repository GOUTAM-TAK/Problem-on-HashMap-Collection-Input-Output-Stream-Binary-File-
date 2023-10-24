package TaskManagementSystem.tester;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import TaskManagementSystem.core.Status;
import TaskManagementSystem.core.Task;
import utility.TaskSupportOperation;

public class TaskManager implements TaskSupportOperation{

	public static void main(String[] args) {
		try(Scanner sc = new Scanner(System.in)){
			Map<String,Task>taskMap=new HashMap<>();
			//taskMap = TaskSupportOperation.populatedTask(taskMap);
			taskMap = TaskSupportOperation.readTaskFile();
			boolean exit=false;
			while(!exit) {
				System.out.println("choose any option :\n"
						+ "1)Add new Task\n"
						+ "2)Delete a Task\n"
						+ "3)Update a task Status\n"
						+ "4)Display all pending task\n"
						+ "5)Display all pending tasks of today\n"
						+ "6)Display all tasks sorted by taskdate\n"
						+ "0)Exit");
				try {
				switch(sc.nextInt()) {
				case 1:TaskSupportOperation.checkInput(taskMap, sc);
					break;
				case 2:System.out.println("enter task name want to be deleted");
				       String name = sc.next();
				       taskMap.get(name).setActive(false);
				       System.out.println("successfully delete task");
					break;
				case 3:
					TaskSupportOperation.setStatusTasks(taskMap, sc);
					break;
				case 4:
					taskMap.values().stream().filter(p->p.getStatus()==Status.PENDING).sorted((p1,p2)->p1.getTaskId().compareTo(p2.getTaskId())).forEach(System.out::println);
					
					break;
				case 5:taskMap.values().stream().filter(p->p.getTaskDate().equals(LocalDate.now())).forEach(System.out::println);
					break;
				case 6:taskMap.values().stream().sorted((p1,p2)->p1.getTaskDate().compareTo(p2.getTaskDate())).forEach(System.out::println);
					break;
				case 0:System.out.println("Thank you!!");
				       exit=true;
					break;
				}
				}
				catch(Exception e) {
					e.printStackTrace();
					sc.nextLine();
				}
			}
			TaskSupportOperation.writeTaskInFile(taskMap);
			System.out.println("successfully store data in 'UpdatedTaskFile.ser'");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
