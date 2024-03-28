package it.pink.pink.Repository;


import it.pink.pink.Entity.Carello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository

public interface CarellloRepository extends JpaRepository<Carello, UUID>, PagingAndSortingRepository<Carello, UUID> {
}
