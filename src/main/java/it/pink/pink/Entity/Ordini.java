package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import it.pink.pink.Entity.Enum.StatoOrdine;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Ordini {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;
    @Column(name = "data_ordine")
    private LocalDate dataOrdine;
    @Column(name = "totale_costo")
    private double TotaleCosto;
    @Column(name = "stato_ordine")
    private StatoOrdine statoOrdine;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private Utente utente;
    @OneToOne(mappedBy = "ordini")
    @JsonIgnore
    private Fattura fattura;
    @OneToOne
@JoinColumn(name = "carrello_id")
    @JsonIgnore
    private  Carello carello;

    @OneToMany (mappedBy = "ordini")
    private List<Prodotto> prodottiList;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name="indirizzo_id")
    private Indirizzo indirizzo;

    public Ordini(LocalDate dataOrdine, double totaleCosto, StatoOrdine statoOrdine,List<Prodotto> prodottiList,Carello carello) {
        this.dataOrdine = dataOrdine;
       this.TotaleCosto = totaleCosto;
        this.statoOrdine = statoOrdine;
        this.prodottiList=prodottiList;
        this.carello=carello;
        // this.sconto=new Sconto();}
    }
    public Ordini() {
        dataOrdine=LocalDate.now();
    }

    public void addProdotti(Prodotto prodotto){
       if (this.prodottiList==null){
           this.prodottiList=new ArrayList<>();
       }
        this.prodottiList.add(prodotto);
       for (Prodotto prodott : prodottiList) {
           this.TotaleCosto += prodott.getPrezzo();
       }
    }

}
