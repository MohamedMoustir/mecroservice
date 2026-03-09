package com.streaming.user.dto;
import jakarta.validation.constraints.*;
import lombok.Data;
@Data
public class UserRequestDTO {
    @NotBlank(message = "Le username est obligatoire")
    @Size(min = 3, max = 50)
    private String username;
    @NotBlank @Email(message = "Format email invalide")
    private String email;
    @NotBlank
    @Size(min = 6, message = "Le mot de passe doit faire au moins 6 caracteres")
    private String password;
}