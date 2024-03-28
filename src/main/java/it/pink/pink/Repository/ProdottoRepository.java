package it.pink.pink.Repository;

import it.pink.pink.Entity.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProdottoRepository  extends JpaRepository<Prodotto,  UUID>, PagingAndSortingRepository<Prodotto, UUID> {
    List<Prodotto> findByName(String nome);

    @Query("SELECT p FROM Prodotto p WHERE p.id = :id")
    Prodotto getProdottoById(@Param("id") UUID id);

}
