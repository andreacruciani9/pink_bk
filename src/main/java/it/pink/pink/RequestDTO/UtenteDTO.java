package it.pink.pink.RequestDTO;

import it.pink.pink.Entity.Carello;
import it.pink.pink.Entity.Indirizzo;
import it.pink.pink.Entity.Ordini;
import it.pink.pink.Entity.Recensioni;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;
import java.util.UUID;

public record UtenteDTO (
        @NotBlank(message = "nome obbligatorio")
    String nome,
        @NotBlank(message = "Il campo cognome non può essere vuoto/null")
    String cognome,
        @NotBlank(message = "Il campo username non può essere vuoto/null")
    String username,
        @Email(regexp = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$", message = "email non valida")
    String email,
        @NotBlank(message = "Il campo password non può essere vuoto/null")
    String password,
        List<Recensioni> recensioni,
        List<Indirizzo> indirizzo,
        Carello carello,
        @Transient
        UUID indirizzoId,
        IndirizzoDTO indirizzoregistrato,
    List<Ordini> ordinis
    ){}
