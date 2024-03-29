package it.pink.pink.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.pink.pink.Entity.Enum.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;


import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
public class Utente {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private String cognome;

    private String username;

    private String password;

    private String email;
    private Timestamp createdAt;
    @OneToMany(mappedBy = "utente")
    private List<Recensioni> recensioni;

    @OneToMany(mappedBy = "utente", cascade = CascadeType.ALL)
    private List<Indirizzo> indirizzo = new ArrayList<>();

    @OneToOne(mappedBy = "utente")
    private Carello carello;
    @OneToMany(mappedBy = "utente")
    private List<Fattura> fatture = new ArrayList<>();

    @OneToMany(mappedBy = "utente")
    private List<Ordini> ordinis = new ArrayList<>();
    @OneToOne(mappedBy = "utente", cascade = CascadeType.ALL)
    private Sconto sconto;

    private List<Role> ruolo = List.of(Role.CLIENTE);
    @Transient
    private UUID indirizzoId;

    public Utente(String username, String email, String password, String nome, String cognome) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.nome = nome;
        this.cognome = cognome;
        this.indirizzo=new ArrayList<>();
        createdAt = Timestamp.valueOf(LocalDateTime.now());

    }




    public void agginginuovoindirizzo(Indirizzo indirizzo) {

       this.indirizzo.add(indirizzo);
      getIndirizzo().add(indirizzo);
    }

       @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        HashSet<GrantedAuthority> authorities = new HashSet<GrantedAuthority>(ruolo.size());

        for (Role r : ruolo)
            authorities.add(new SimpleGrantedAuthority(r.name()));

        return authorities;
    }

    @Override
    public String toString() {
        return "";
    }


}
