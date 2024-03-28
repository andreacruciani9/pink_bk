package it.pink.pink.RequestDTO;

import it.pink.pink.Entity.Enum.TipiCategorie;
import it.pink.pink.Entity.Ordini;
import it.pink.pink.Entity.Recensioni;
import it.pink.pink.Entity.Sconto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.util.List;

public record ProdottoDTO(
        @NotBlank(message = "nome obbligatorio")
        String name,
        @NotBlank(message = "descrizione obbligatorio")
        String descrizione,
        @NotNull
        double prezzo,
        String url,
        @PositiveOrZero(message = "Il numero di prodotti deve essere maggiore o uguale a 0")
        int quantita,
        TipiCategorie tipiCategorie,
         Ordini ordini,
        List<Recensioni> recensione,
        Sconto sconto

) {
}
