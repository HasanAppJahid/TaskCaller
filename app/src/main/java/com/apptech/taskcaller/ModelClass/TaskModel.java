package com.apptech.taskcaller.ModelClass;

public class TaskModel {
    private String title;
    private String date;
    private String time;
    private boolean completed; // single field for completion status

    // Constructor
    public TaskModel(String title, String date, String time, boolean completed) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.completed = completed;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public boolean isCompleted() { return completed; }

    // Setters
    public void setTitle(String title) { this.title = title; }
    public void setDate(String date) { this.date = date; }
    public void setTime(String time) { this.time = time; }
    public void setCompleted(boolean completed) { this.completed = completed; }
}
