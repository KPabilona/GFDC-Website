package com.capstone.dentalclinic.demo.model;

public enum MaritalStatus {
    MARRIED("Married"),
    SINGLE("Single"),
    WIDOWED("Widowed"),
    DIVORCE("Divorce"),
    SEPARATED("Separated");

    private final String displayMaritalStatus;

    MaritalStatus(String displayMaritalStatus) {
        this.displayMaritalStatus = displayMaritalStatus;
    }

    public String getMaritalStatus() {
        return displayMaritalStatus;
    }

}
