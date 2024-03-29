package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
public class Recensioni {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String commenti;
    private LocalDate data;
    private int valutazione;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private Utente utente;


    @ManyToOne
    @JoinColumn(name = "prodotto_id")
    @JsonIgnore
    private Prodotto prodotto;


    public Recensioni(String commenti, LocalDate data, int valutazione,Prodotto prodotto) {
        this.commenti = commenti;
        this.data = data;
        this.valutazione = valutazione;
        this.prodotto=prodotto;
    }
}
