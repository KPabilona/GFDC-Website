package com.capstone.dentalclinic.demo.model;

public enum Roles {
    ADMIN("ADMIN"),
    PATIENT("PATIENT");

    private final String displayRole;

    Roles(String displayRole) {
        this.displayRole = displayRole;
    }

    public String getDisplayRole() {
        return displayRole;
    }
}
