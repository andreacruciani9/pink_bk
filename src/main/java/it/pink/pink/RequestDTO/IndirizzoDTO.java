package it.pink.pink.RequestDTO;

import it.pink.pink.Entity.Ordini;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record IndirizzoDTO (
    @NotBlank(message = "Via obbligatorio")
    String via,
    @NotBlank(message = "Civico obbligatorio")

    String civico,
    @NotBlank(message = "localita obbligatorio")

    String localita,
    @NotBlank(message = "cap obbligatorio")

    String cap


    )

    {}
