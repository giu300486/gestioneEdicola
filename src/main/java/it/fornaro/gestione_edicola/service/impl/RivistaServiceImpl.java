package it.fornaro.gestione_edicola.service.impl;

import it.fornaro.gestione_edicola.model.Rivista;
import it.fornaro.gestione_edicola.repository.RivistaRepository;
import it.fornaro.gestione_edicola.service.RivistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
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
        Rivista rivistaFound = this.rivistaRepository.findByIdRivista(rivista.getIdRivista());
        if(Objects.isNull(rivistaFound)) {
            rivistaFound = this.rivistaRepository.findByBarCode(rivista.getBarCode());
            if(!Objects.isNull(rivistaFound)) return null;
            rivista.setDataCreazione(new Date());
        }
        else {
            rivista.setDataAggiornamento(new Date());
        }
        return this.rivistaRepository.save(rivista);
    }

    @Override
    public List<Rivista> findAll() {
        return this.rivistaRepository.findAll();
    }

    @Override
    public Rivista findByBarcode(String barcode) {
        return this.rivistaRepository.findByBarCode(barcode);
    }

    @Override
    public void delete(Rivista rivista) {
        this.rivistaRepository.delete(rivista);
    }
}
