package TaskManagementSystem.core;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/*You can create a class Task with fields like taskId,
 *  taskName, description, taskDate, status, active. 
taskId should be unique and generated automatically.
status should be either PENDING, IN PROGRESS or COMPLETED.
active should be either true or false. Deleted task will have active=false 
Newly added task should have default status as PENDING and active=true
*/
public class Task implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private static int cnt=0;
     private Integer taskId;
     private String name; //primary key
     private String desciption;
     private LocalDate taskDate;
     private Status status;
     private boolean active;
	public Task(String name, String desciption, LocalDate taskDate) {
		super();
		this.taskId=++cnt;
		this.name = name;
		this.desciption = desciption;
		this.taskDate = taskDate;
		this.status=Status.PENDING;
		this.active=true;
	}
	@Override
	public String toString() {
		return "Task [taskId=" + taskId + ", name=" + name + ", desciption=" + desciption + ", taskDate=" + taskDate
				+ ", status=" + status + ", active=" + active + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Task) {
			Task t = (Task)obj;
			return this.name.equals(t.name);
		}
		return false;
	}
	public int compareTo(Task t) {
		return this.name.compareTo(t.name);
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public LocalDate getTaskDate() {
		return this.taskDate;
	}
	public Integer getTaskId() {
		return taskId;
	}
	
	
     
}
