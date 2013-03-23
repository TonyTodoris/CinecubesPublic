/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package TaskMgr;

import java.util.ArrayList;

/**
 *
 * @author Asterix
 */
public class TaskMgr {
    private ArrayList<Task> Tasks;
    
    public TaskMgr(){
    	
    }
    
    public void createTasks(){
    	Tasks=new ArrayList<>();  	
    }

    public Task createNewTask(){
    	Task newTask=new Task();
    	Tasks.add(newTask);
    	return newTask;
    }
    
    public Task getTask(int i){
    	return Tasks.get(i);
    }
    
    private int getNumOfTasks(){
    	return Tasks.size();
    }
    
    public Task getLastTask(){
    	return getTask(getNumOfTasks()-1);
    }
    
	public ArrayList<Task> getTasks() {
		return Tasks;
	}

	public void setTasks(ArrayList<Task> tsks) {
		Tasks = tsks;
	};
	
	
}