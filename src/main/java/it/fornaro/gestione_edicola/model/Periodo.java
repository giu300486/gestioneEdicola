package it.fornaro.gestione_edicola.model;

import java.io.Serializable;

public enum Periodo implements Serializable {
    giornaliero("Giornaliero"),
    settimanale("Settimanale"),
    mensile("Mensile"),
    trimestrale("Trimestrale"),
    semestrale("Semestrale"),
    annuale("Annuale");


    private final String periodo;

    Periodo(String periodo) {
        this.periodo = periodo;
    }

    public String getPeriodo() {
        return periodo;
    }

    @Override
    public String toString() {
        return periodo;
    }
}
