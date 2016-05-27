package com.memorius.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by dpivovar on 23.05.2016.
 */
@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String description;
    private Date deadline;
    private String creator;
    private String notificationFrequency;

    public Goal(String name, String description, Date deadline, String creator, String notificationFrequency) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.creator = creator;
        this.notificationFrequency = notificationFrequency;
    }

    public Goal() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getNotificationFrequency() {
        return notificationFrequency;
    }

    public void setNotificationFrequency(String notificationsFrequency) {
        this.notificationFrequency = notificationsFrequency;
    }
}
