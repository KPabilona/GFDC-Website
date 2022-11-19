package com.capstone.dentalclinic.demo.model;

public enum EmployeeRole {
    ADMIN("ADMIN"),
    SUPER_ADMIN("SUPER_ADMIN");

    private final String displayRole;

    EmployeeRole(String displayRole) {
        this.displayRole = displayRole;
    }

    public String getDisplayRole() {
        return displayRole;
    }
}
