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
    public Rivista salva(Rivista rivista) {
        Rivista rivistaFound = this.rivistaRepository.findByBarCode(rivista.getBarCode());
        if(!Objects.isNull(rivistaFound)) return new Rivista();
        rivista.setDataCreazione(new Date());
        return this.rivistaRepository.save(rivista);
    }
}
