package it.pink.pink.RequestDTO;

import it.pink.pink.Entity.Prodotto;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;
import java.util.UUID;

public record RecensioniDTO(
        @NotBlank(message = "commenti obbligatorio")

        String commenti,
        @NotBlank(message = "data obbligatorio")

        LocalDate data,
        @NotBlank(message = "valutazione obbligatorio")

        int valutazione,
        UUID idProdotto

    )
{}
