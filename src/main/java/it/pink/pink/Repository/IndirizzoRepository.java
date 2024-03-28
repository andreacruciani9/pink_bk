package it.pink.pink.Repository;


import it.pink.pink.Entity.Indirizzo;
import it.pink.pink.Entity.Utente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository

public interface IndirizzoRepository extends JpaRepository<Indirizzo, UUID>, PagingAndSortingRepository<Indirizzo, UUID> {
    public List<Indirizzo> findByUtente(Utente utente);
    @Query("SELECT i.utente FROM Indirizzo i WHERE i.utente.id = :utenteId")
    Utente findUtenteByIndirizzo(@Param("utenteId") UUID utenteId);

}
