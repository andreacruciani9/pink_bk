package it.pink.pink.Repository;

import it.pink.pink.Entity.Ordini;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface OrdineRepository  extends JpaRepository<Ordini, UUID>, PagingAndSortingRepository<Ordini, UUID> {
}
