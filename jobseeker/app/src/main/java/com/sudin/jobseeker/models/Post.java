package com.sudin.jobseeker.models;

import java.io.Serializable;

public class Post implements Serializable {
    private String _id;
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return _id;
    }

    public void setId(String _id) {
        this._id = _id;
    }

    @Override
    public String toString() {
        return "PostInterface{" +
                "id='" + _id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public Post(String description) {
        this.description = description;
    }

    public Post(String _id, String description) {
        this._id = _id;
        this.description = description;
    }
}
