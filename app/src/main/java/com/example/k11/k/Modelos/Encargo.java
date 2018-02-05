package com.example.k11.k.Modelos;

import java.math.BigInteger;

/**
 * Created by k11 on 25/01/18.
 */

public class Encargo {
    private int idEncargo,cantidadEnc,costoEnc;
    private String nomEnc,descEnc,nomCli,estadoCompra,celCliente;

    public int getIdEncargo() {
        return idEncargo;
    }

    public void setIdEncargo(int idEncargo) {
        this.idEncargo = idEncargo;
    }

    public int getCantidadEnc() {
        return cantidadEnc;
    }

    public void setCantidadEnc(int cantidadEnc) {
        this.cantidadEnc = cantidadEnc;
    }

    public int getCostoEnc() {
        return costoEnc;
    }

    public void setCostoEnc(int costoEnc) {
        this.costoEnc = costoEnc;
    }

    public String getCelCliente() {
        return celCliente;
    }

    public void setCelCliente(String celCliente) {
        this.celCliente = celCliente;
    }

    public String getNomEnc() {
        return nomEnc;
    }

    public void setNomEnc(String nomEnc) {
        this.nomEnc = nomEnc;
    }

    public String getDescEnc() {
        return descEnc;
    }

    public void setDescEnc(String descEnc) {
        this.descEnc = descEnc;
    }

    public String getNomCli() {
        return nomCli;
    }

    public void setNomCli(String nomCli) {
        this.nomCli = nomCli;
    }

    public String getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(String estadoCompra) {
        this.estadoCompra = estadoCompra;
    }
}
