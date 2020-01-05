package com.sudin.jobseeker.models;

public class RegistrationResponse
{
    public boolean success;
    public String status;

    public RegistrationResponse(boolean success, String status) {
        this.success = success;
        this.status = status;

    }


    public boolean getSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
