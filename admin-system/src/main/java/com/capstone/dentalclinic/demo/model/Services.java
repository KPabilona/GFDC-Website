package com.capstone.dentalclinic.demo.model;

public enum Services {
    ORAL_PROPHYLAXIS("Oral Prophylaxis"),
    TOOTH_EXTRACTION("tooth Extraction"),
    ORTHODONTICS("Orthodontics"),
    TOOTH_RESTORATION("Tooth Retoration"),
    REMOVABLE_DENTURES("Removable Dentures"),
    PARTIAL_DENTURES("Partial Dentures"),
    FIXED_PARTIAL_DENTURES("Fixed Partial Dentures"),
    DIGITAL_PANORAMIC_XRAY("Digital Panoramic Xray");

    private final String displayServices;

    Services(String services) {
        this.displayServices = services;
    }

    public String getDisplayServices() {
        return displayServices;
    }
}
