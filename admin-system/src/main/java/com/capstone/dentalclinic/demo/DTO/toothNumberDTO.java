package com.capstone.dentalclinic.demo.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class toothNumberDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String toothNumber;
}
