package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@JsonIgnoreProperties
public class Fattura {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
     private UUID id;
    private int numero;

    private LocalDate data;

    private double importo;

    @ManyToOne
    @JoinColumn(name = "utente_id")
    @JsonIgnore
    private Utente utente;
    @OneToOne
    @JoinColumn(name="Ordini_id")
            private Ordini ordini;

    public Fattura(LocalDate data, double importo, int numero,Ordini ordini
    ) {
        this.ordini=ordini;

        this.importo=importo;
        this.data=data;
        this.numero=numero;
    }
    public  void gettotale(Ordini ordini){

        double totaleCosto=ordini.getTotaleCosto();
        this.importo=totaleCosto;
    }
}
