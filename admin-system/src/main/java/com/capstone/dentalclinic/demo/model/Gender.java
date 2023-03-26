package com.capstone.dentalclinic.demo.model;

public enum Gender {
    MALE ("Male"),
    FEMALE ("Female"),
    PREFERRED_NOT_TO_SAY("Preferred not to say");

    private final String displayGender;
    
    Gender(String displayGender) {
            this.displayGender = displayGender;
    }

    public String getDisplayGender() {
        return displayGender;
    }
}
