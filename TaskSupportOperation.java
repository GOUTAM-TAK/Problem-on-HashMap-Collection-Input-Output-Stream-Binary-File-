package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.Map;
import java.util.Scanner;

import TaskManagementSystem.core.Status;
import TaskManagementSystem.core.Task;
import exception.TaskException;

public interface TaskSupportOperation {
             static void validKey(Map<String,Task>taskMap,String name) throws TaskException {
            	 if(taskMap.containsKey(name))
            		 throw new TaskException("duplicate Task not allowed!!!");	 
             }
             static Map<String,Task> checkInput(Map<String,Task>taskMap,Scanner sc) throws TaskException{
            	 System.out.println("enter name of task,discription,creation date");
            	 String name=sc.next();
            	 Task newTask =new Task(name,sc.next(),LocalDate.parse(sc.next()));
            	if(taskMap.putIfAbsent(name,newTask)!=null)
            			throw new TaskException("duplicate is not allowed!!!");
            	System.out.println("successfully task created");
            	return taskMap;
             }
             static void setStatusTasks(Map<String,Task>taskMap,Scanner sc) throws TaskException {
            	 System.out.println("enter name of task want to be change status");
            	 String name =sc.next();
            	 if(taskMap.containsKey(name)) {
            		 Task t = taskMap.get(name);
            		 System.out.println("enter new status[pending,complete,in_progress]");
            		 t.setStatus(Status.valueOf(sc.next().toUpperCase()));
            	 }
            	 else {
            	 throw new TaskException("Task not found!!!");
            	 }
             }
             static Map<String,Task> populatedTask(Map<String,Task>taskMap){
            	 taskMap.putIfAbsent("sleep", new Task("sleep","now sleeping time",LocalDate.parse("2023-06-17")));
              	 taskMap.putIfAbsent("runing", new Task("runing","runing good for health",LocalDate.parse("2023-10-22")));
              	 taskMap.putIfAbsent("study", new Task("study","now study time",LocalDate.parse("2023-10-24")));
              	 taskMap.putIfAbsent("lunch", new Task("luch","now luch time",LocalDate.parse("2023-10-24")));
           return taskMap;
             }
             static void writeTaskInFile(Map<String,Task>taskMap) throws FileNotFoundException, IOException {
            	 try(ObjectOutputStream out=new ObjectOutputStream(new FileOutputStream("UpdatedTaskFile.ser"))){
            		 out.writeObject(taskMap);
            	 }
             }
             @SuppressWarnings("unchecked")
			static Map<String,Task> readTaskFile() throws FileNotFoundException, IOException, ClassNotFoundException{
            	 try(ObjectInputStream in =new ObjectInputStream(new FileInputStream("TaskFile.ser"))){
            		 return (Map<String,Task>)in.readObject();
            	 }
             }
}
