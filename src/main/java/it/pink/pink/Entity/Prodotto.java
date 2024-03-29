package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.pink.pink.Entity.Enum.TipiCategorie;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@Data
@Entity


public class Prodotto {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String descrizione;
    private double prezzo;

    private List<String>listaimmagini = new ArrayList<>();
    private int quantità;
    @Enumerated(EnumType.STRING)
    private TipiCategorie tipiCategorie;
    @ManyToOne
    @JoinColumn(name = "ordini_id")
    @JsonIgnore
    private Ordini ordini;
    @ManyToOne
    @JoinColumn(name = "carello_id")
    private Carello carello;

    @OneToMany(mappedBy = "prodotto")
    private List<Recensioni> recensione=new ArrayList<>();
    @OneToOne(mappedBy = "prodotto")
    private Sconto sconto;
    public Prodotto(String name, String descrizione, double prezzo, int quantità,Ordini ordini,TipiCategorie tipiCategorie) {
        this.name = name;
        this.descrizione = descrizione;
        this.prezzo = prezzo;

        this.quantità = quantità;
        this.ordini=ordini;
        this.tipiCategorie=tipiCategorie;
        // this.recensione=new ArrayList<>();
        List<String> listaimmagini=new ArrayList<>();
    }

    public Prodotto(){}

public void getImmagini(String url){

        this.listaimmagini = new ArrayList<>();


      listaimmagini.add(url);

}
}
