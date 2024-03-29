package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;
@Data
@Entity
@JsonIgnoreProperties

public class Indirizzo {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Setter(AccessLevel.NONE)
    private UUID id;

    private String via;

    private String civico;

    private String localita;
    private String cap;

    @Transient
    private UUID utendeId;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "utente_id")
    private Utente utente;
    @OneToOne(mappedBy="indirizzo")
    private Ordini ordini;



    public Indirizzo() {
    }

    public Indirizzo(String via, String civico, String localita, String cap, Utente utente) {
        this.via = via;
        this.civico = civico;
        this.localita = localita;
        this.cap = cap;
        this.utente = utente;
    }
}
