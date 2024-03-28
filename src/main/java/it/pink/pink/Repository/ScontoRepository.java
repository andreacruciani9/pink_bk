package it.pink.pink.Repository;


import it.pink.pink.Entity.Sconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface ScontoRepository extends JpaRepository<Sconto, String>, PagingAndSortingRepository<Sconto, String> {
}