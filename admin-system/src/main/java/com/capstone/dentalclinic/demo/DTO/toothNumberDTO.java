package com.capstone.dentalclinic.demo.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class toothNumberDTO {

    private Long id;

    @NotNull
    @NotBlank
    private String toothNumber;
}
