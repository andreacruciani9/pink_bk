package it.pink.pink.RequestDTO;


import jakarta.validation.constraints.NotBlank;

import java.util.UUID;


public record LoginDTO(
        UUID userId ,
        @NotBlank(message = "Lo username è obbligatorio e non può essere un campo vuoto")
        String username,
        @NotBlank(message = "La password è obbligatoria e non puà essere un campo vuoto")
        String password
        
) {}
