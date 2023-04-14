package com.capstone.dentalclinic.demo.DTO;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdminDashboardDateTimeDTO {

    @NotNull(message = "Time is Required!")
//    @FutureOrPresent(message = "Invalid Time Format")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate pickDate;
}
