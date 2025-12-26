package com.example.groupassignment.Admin;

public class Changes {
    private String date;
    private String category;
    private String description;
    private String doneBy;

    // getters (used by TableView)
    public String getDate() { return date; }
    public String getCategory() { return category; }
    public String getDescription() { return description; }
    public String getDoneBy() { return doneBy; }

    // setters (used when you fill an object manually or by Gson)
    public void setDate(String date) { this.date = date; }
    public void setCategory(String category) { this.category = category; }
    public void setDescription(String description) { this.description = description; }
    public void setDoneBy(String doneBy) { this.doneBy = doneBy; }
}

