package it.pink.pink.RequestDTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.pink.pink.Entity.Ordini;
import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Entity.Utente;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

public record CarelloDTO(
        @NotBlank(message = "importo totale obbligatorio")

        double importo_totale,
        @NotBlank(message = "lista Prodotto obbligatorio")
         List<Prodotto> prodotti,
       @JsonIgnore

         Ordini ordine,
       @JsonIgnore
        Utente utente
) {

}
