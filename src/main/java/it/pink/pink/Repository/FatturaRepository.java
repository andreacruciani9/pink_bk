package it.pink.pink.Repository;

import it.pink.pink.Entity.Fattura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface FatturaRepository  extends JpaRepository<Fattura, UUID>, PagingAndSortingRepository<Fattura, UUID> {

    List<Fattura> findByUtenteUsername(String username);}
