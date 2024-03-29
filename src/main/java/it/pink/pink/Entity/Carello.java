package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties
public class Carello {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private double importo_totale;

    @OneToMany(mappedBy = "carello")
    private List<Prodotto> prodotti=new ArrayList<>();

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @OneToOne(mappedBy = "carello")
    @JsonIgnore
    private Ordini ordine;

    public Carello( Utente utente) {
        //this.importo_totale = importo_totale;
        //this.prodotti = prodotti;
        this.utente = utente;
    }





    public void addProdotti(Prodotto prodotto) {

        prodotti.add(prodotto);
        for (Prodotto prodott:prodotti) {
            this.importo_totale += prodott.getPrezzo();
        }
    }
}
