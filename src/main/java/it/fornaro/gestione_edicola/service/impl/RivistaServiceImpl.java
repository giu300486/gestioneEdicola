package it.fornaro.gestione_edicola.service.impl;

import it.fornaro.gestione_edicola.model.Rivista;
import it.fornaro.gestione_edicola.repository.RivistaRepository;
import it.fornaro.gestione_edicola.service.RivistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class RivistaServiceImpl implements RivistaService {

    private final RivistaRepository rivistaRepository;

    @Autowired
    public RivistaServiceImpl(RivistaRepository rivistaRepository) {
        this.rivistaRepository = rivistaRepository;
    }

    @Override
    public boolean salva(Rivista rivista) {
        rivista.setDataCreazione(new Date());
        return !Objects.isNull(this.rivistaRepository.saveAndFlush(rivista));
    }
}
