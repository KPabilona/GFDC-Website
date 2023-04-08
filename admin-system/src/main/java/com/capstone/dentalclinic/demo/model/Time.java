package com.capstone.dentalclinic.demo.model;

import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public enum Time {
    ELEVEN_AM(LocalTime.of(11,00)),
    TWELVE_NOON(LocalTime.of(12,00)),
    ONE_PM(LocalTime.of(13,00)),
    TWO_PM(LocalTime.of(14,00)),
    THREE_PM(LocalTime.of(15,00)),
    FOUR_PM(LocalTime.of(16,00)),
    FIVE_PM(LocalTime.of(17,00)),
    SIX_PM(LocalTime.of(18,00));
    private final LocalTime displayTime;

    Time(LocalTime displayTime) {
        this.displayTime = displayTime;
    }

    public String getDisplayTime() {
        ZoneId zoneId = ZoneId.of("Asia/Manila");
        DateTimeFormatter FORMAT = DateTimeFormatter.ofPattern("hh:mm a").withZone(zoneId);
        String displayTime = FORMAT.format(this.displayTime);
        System.out.println("DISPLAY TIME" + displayTime);
        return displayTime;
    }
}
