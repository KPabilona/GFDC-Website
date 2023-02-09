package com.capstone.dentalclinic.demo.model.admin;

public enum Gender {
    MALE ("Male"),
    FEMALE ("Female");

    private final String displayGender;
    
    Gender(String displayGender) {
            this.displayGender = displayGender;
    }

    public String getDisplayGender() {
        return displayGender;
    }
}
