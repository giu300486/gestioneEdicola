package it.fornaro.gestione_edicola.model;

import java.io.Serializable;

public class Rivista implements Serializable, Comparable<Rivista> {

    private String barcode;
    private String descrizione;
    private Double prezzo;
    private Integer numero;
    private Integer quantita;
    private Periodo periodo;

    public Rivista() {}

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
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

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getQuantita() {
        return quantita;
    }

    public void setQuantita(Integer quantita) {
        this.quantita = quantita;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    @Override
    public int compareTo(Rivista r) {
        return this.barcode.compareTo(r.barcode);
    }

    @Override
    public String toString() {
        return "Rivista{" +
                "barcode='" + barcode + '\'' +
                ", descrizione='" + descrizione + '\'' +
                ", prezzo=" + prezzo +
                ", numero=" + numero +
                ", quantita=" + quantita +
                ", periodo=" + periodo +
                '}';
    }
}
