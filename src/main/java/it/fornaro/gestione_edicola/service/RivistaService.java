package it.fornaro.gestione_edicola.service;

import it.fornaro.gestione_edicola.model.Rivista;

import java.util.List;

public interface RivistaService {

    Rivista salva(Rivista rivista);
    List<Rivista> findAll();

}
