package com.sudin.jobseeker.models;

import java.io.Serializable;

public class jobpost implements Serializable {
    private String id;
    private String name;
    private String Applicant;

    private String Description;
    private String Deadline;
    private String JobType;

    public jobpost(String id, String name, String applicant, String description, String deadline, String jobType) {
        this.id = id;
        this.name = name;
        Applicant = applicant;
        Description = description;
        Deadline = deadline;
        JobType = jobType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicant() {
        return Applicant;
    }

    public void setApplicant(String applicant) {
        Applicant = applicant;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDeadline() {
        return Deadline;
    }

    public void setDeadline(String deadline) {
        Deadline = deadline;
    }

    public String getJobType() {
        return JobType;
    }

    public void setJobType(String jobType) {
        JobType = jobType;
    }

    @Override
    public String toString() {
        return "jobpost{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", Applicant='" + Applicant + '\'' +
                ", Description='" + Description + '\'' +
                ", Deadline='" + Deadline + '\'' +
                ", JobType='" + JobType + '\'' +
                '}';
    }
}
