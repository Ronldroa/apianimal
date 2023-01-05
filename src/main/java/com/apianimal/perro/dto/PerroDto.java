package com.apianimal.perro.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class PerroDto {

    private Integer id;
    @NotBlank
    private String nombre;
    @NotBlank
    private String raza;
    @NotBlank
    private String color;
    @Digits(integer = 3, fraction = 2, message = "Limite los dígitos enteros a 3 y los dígitos decimales a 2")
    private int edad;
    @NotBlank
    private String sexo;

    private double peso;

}
