package com.example.regischarles.sometodo;

public class Task {
    private String task,status,username;
    public Task(String task,String status,String username){
        this.setUsername(username);
        this.setStatus(status);
        this.setTask(task);
    }
public Task(){

}
    public String getTask() {
        return task;
    }


    public void setTask(String task) {
        this.task = task;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}