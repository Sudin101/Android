package com.sudin.jobseeker.models;

public class apply {
    private String _id;
    private String jobpost;
    private String cv;

    public apply(String jobpost, String cv) {
        this.jobpost = jobpost;
        this.cv = cv;
    }

    public String getJobpost() {
        return jobpost;
    }

    public void setJobpost(String jobpost) {
        this.jobpost = jobpost;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    @Override
    public String toString() {
        return "apply{" +
                "_id='" + _id + '\'' +
                ", jobpost='" + jobpost + '\'' +
                ", cv='" + cv + '\'' +
                '}';
    }
}
