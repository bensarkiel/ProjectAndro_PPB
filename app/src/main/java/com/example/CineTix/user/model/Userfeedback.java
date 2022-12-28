package com.example.CineTix.user.model;

public class Userfeedback {
    String id;
    String username;
    String ulasan;
    String feedback;

    public Userfeedback() {
    }

    public Userfeedback(String id, String username, String ulasan, String feedback) {
        this.id = id;
        this.username = username;
        this.ulasan = ulasan;
        this.feedback = feedback;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUlasan() {
        return ulasan;
    }

    public void setUlasan(String ulasan) {
        this.ulasan = ulasan;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

}
