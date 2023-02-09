package it.fornaro.gestione_edicola.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "rivista")
public class Rivista implements Serializable, Comparable<Rivista> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_gestione_edicola")
    @SequenceGenerator(sequenceName = "sequence_gestione_edicola", allocationSize = 1, name = "seq_gestione_edicola")
    @Column(name = "id_rivista")
    private Long idRivista;

    @Column(name = "bar_code")
    private String barCode;

    @Column(name = "descrizione")
    private String descrizione;

    @Column(name = "prezzo")
    private Double prezzo;

    @Column(name = "numero_rivista")
    private Integer numeroRivista;

    @Column(name = "giacenze")
    private Integer giacenze;

    @Column(name = "periodo")
    private Periodo periodo;

    @Column(name = "data_creazione")
    private Date dataCreazione;

    @Column(name = "data_aggiornamento")
    private Date dataAggiornamento;

    public Rivista() {}

    public Long getIdRivista() {
        return idRivista;
    }

    public void setIdRivista(Long idRivista) {
        this.idRivista = idRivista;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public Double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(Double prezzo) {
        this.prezzo = prezzo;
    }

    public Integer getNumeroRivista() {
        return numeroRivista;
    }

    public void setNumeroRivista(Integer numeroRivista) {
        this.numeroRivista = numeroRivista;
    }

    public Integer getGiacenze() {
        return giacenze;
    }

    public void setGiacenze(Integer giacenze) {
        this.giacenze = giacenze;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public Date getDataCreazione() {
        return dataCreazione;
    }

    public void setDataCreazione(Date dataCreazione) {
        this.dataCreazione = dataCreazione;
    }

    public Date getDataAggiornamento() {
        return dataAggiornamento;
    }

    public void setDataAggiornamento(Date dataAggiornamento) {
        this.dataAggiornamento = dataAggiornamento;
    }

    @Override
    public int compareTo(Rivista r) {
        return this.barCode.compareTo(r.barCode);
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "idRivista=" + idRivista +
                ", barCode='" + barCode + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", numeroRivista=" + numeroRivista +
                ", giacenze=" + giacenze +
                ", periodo=" + periodo +
                ", dataCreazione=" + dataCreazione +
                ", dataAggiornamento=" + dataAggiornamento +
                '}';
    }
}
