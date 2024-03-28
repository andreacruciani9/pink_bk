package it.pink.pink.Repository;

import it.pink.pink.Entity.Recensioni;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface RecensioneRepository  extends JpaRepository<Recensioni, UUID>, PagingAndSortingRepository<Recensioni, UUID> {
}
