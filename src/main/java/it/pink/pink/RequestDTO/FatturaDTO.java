package it.pink.pink.RequestDTO;

import it.pink.pink.Entity.Ordini;
import it.pink.pink.Entity.Utente;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record FatturaDTO(
        @NotBlank(message = "nr Fattura obbligatoria")
        int numero,
        @NotBlank(message = "data fattura obbligatoria")
        LocalDate data,
        @NotBlank(message = "importo fattura obbligatorio")
        double importo,

        Ordini ordini,

        Utente utente
) {
}
