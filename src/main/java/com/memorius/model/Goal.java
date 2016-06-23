package com.memorius.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotEmpty(message = "{name.empty}")
    @Size(max=200, message = "{name.size}")
    private String name;

    @NotEmpty(message = "{description.empty}")
    @Size(max = 2000, message = "{description.size}")
    private String description;

    @NotNull(message = "{deadline.null}")
    private Date deadline;

    private String creator;

    @NotEmpty(message = "{notificationFrequency.empty}")
    private String notificationFrequency;

    private String status;

    private String participants;

    public Goal(String name, String description, Date deadline, String creator, String notificationFrequency, String status, String participants) {
        this.name = name;
        this.description = description;
        this.deadline = deadline;
        this.creator = creator;
        this.notificationFrequency = notificationFrequency;
        this.status = status;
        this.participants = participants;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getParticipants() {
        return participants;
    }

    public void setParticipants(String participants) {
        this.participants = participants;
    }
}
