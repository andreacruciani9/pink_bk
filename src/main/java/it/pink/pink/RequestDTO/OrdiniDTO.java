package it.pink.pink.RequestDTO;

import it.pink.pink.Entity.Carello;
import it.pink.pink.Entity.Enum.StatoOrdine;
import it.pink.pink.Entity.Indirizzo;
import it.pink.pink.Entity.Prodotto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record OrdiniDTO(
        @NotBlank(message = "data ordine obbligatorio")

        LocalDate dataOrdine,
        @NotBlank(message = "totale obbligatorio")

        double TotaleCosto,
        @NotBlank(message = "stato ordine obbligatorio")

        StatoOrdine statoOrdine,

        Indirizzo indirizzo,
        @NotBlank(message = "stato ordine obbligatorio")

        UUID indirizzoid,
        @NotBlank(message = "stato ordine obbligatorio")
        List<Prodotto>prodottiList,

        Carello carrello
) {
}
