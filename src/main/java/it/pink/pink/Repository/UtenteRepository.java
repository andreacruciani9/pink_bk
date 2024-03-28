package it.pink.pink.Repository;


import it.pink.pink.Entity.Indirizzo;
import it.pink.pink.Entity.Recensioni;
import it.pink.pink.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

import java.util.Optional;
import java.util.UUID;

@Repository

public interface UtenteRepository extends JpaRepository<Utente, UUID>, PagingAndSortingRepository<Utente, UUID> {

    public Optional<Utente> findByEmail(String email);
    @Query("SELECT u.email FROM Utente u")
    public List<String> getAllEmails();
    @Query("SELECT u.username FROM Utente u")
    public List<String> getAllUsernames();

    public Optional<Utente> findByUsername(String username);

    @Query("SELECT u.indirizzo FROM Utente u")
    public List<Indirizzo> getAllIndirizzo();

public Utente save( Indirizzo indirizzo);
   // @Query("SELECT u FROM Utente u WHERE u.indirizzo = :indirizzo")
    //Utente findUtenteByIndirizzo(@Param("indirizzo") Indirizzo indirizzo);
   @Query("SELECT u FROM Utente u WHERE :recensioni MEMBER OF u.recensioni")
   Utente findByRecensioni(List<Recensioni> recensioni);

   // Utente findByOrdiniId(UUID idOrdini);

    @Query("SELECT u FROM Utente u JOIN u.fatture f WHERE f.id = :id")
    Utente findByFatturaByID(UUID id);
}
