package it.pink.pink.RequestDTO;

import it.pink.pink.Entity.Prodotto;
import jakarta.validation.constraints.NotBlank;

public record ScontoDTO(
        @NotBlank(message = "importo sconto obbligatorio")

        String code,
        @NotBlank(message = "importo sconto obbligatorio")

        Double importoSconto,
        @NotBlank(message = "percentuale obbligatorio")

        int percentuale,


         Prodotto prodotto
) {
}
