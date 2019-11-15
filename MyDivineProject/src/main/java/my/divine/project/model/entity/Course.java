package my.divine.project.model.entity;


import my.divine.project.model.constant.State;
import my.divine.project.model.constant.Topic;

import java.io.Serializable;
import java.util.*;


public class Course implements Serializable {

    private int id;
    private User teacher;
    private String name;
    private Topic topic;
    private State state;
    private Date startDate;
    private Date endDate;
    private int count;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }




    @Override
    public String toString() {
        return "Course name: " + name;
    }
}
