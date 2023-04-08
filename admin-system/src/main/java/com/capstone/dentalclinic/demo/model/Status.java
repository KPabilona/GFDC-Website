package com.capstone.dentalclinic.demo.model;

public enum Status {
    APPROVED("Approved"),
    DONE("Done"),
    CANCELLED("Cancelled");

    private final String displayStatus;

    Status(String status){
        this.displayStatus = status;
    }

    public String getDisplayStatus() {
        return displayStatus;
    }
}
