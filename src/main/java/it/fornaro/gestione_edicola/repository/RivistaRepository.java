package it.fornaro.gestione_edicola.repository;

import it.fornaro.gestione_edicola.model.Rivista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RivistaRepository extends JpaRepository<Rivista, Long> {
    Rivista findByIdRivista(Long idRivista);
    Rivista findByBarCode(String barCode);
}
