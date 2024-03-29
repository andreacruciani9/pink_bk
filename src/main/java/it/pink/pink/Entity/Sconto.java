package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.pink.pink.Entity.Prodotto;
import it.pink.pink.Entity.Utente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Data
@Entity

public class Sconto {
    @Id
    private String code;
    @Column(name = "importo_sconto")
    private Double importoSconto;

    private int percentuale;

    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "cliente_id")

    private Utente utente;

    @OneToOne
    @JoinColumn(name = "prodotto_id")
    @JsonIgnore
    private Prodotto prodotto;

    public Sconto(String code, int percentuale, double importoSconto) {
        this.code = code;
        this.importoSconto = importoSconto;
        this.percentuale = percentuale;
        this.prodotto = prodotto;
    }

    public Sconto() {}

    public  void   apllicaSconto(Prodotto prodotti){

        double  prezzo = prodotti.getPrezzo() - importoSconto;
        prodotti.setPrezzo(prezzo);


    }
    public  void  detraisconto(Prodotto prodotti){
        double prezzo_originale=prodotti.getPrezzo();
        prodotti.setPrezzo(prezzo_originale);

    }
}
