/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.Date;

/**
 *
 * @author Ivan
 */
public class Comanda {
    
    int nombreComanda;
    Date dataComanda;
    Date dataDesitjada;
    Date dataEnviament;
    Client email;

    public Comanda(int nombreComanda, Client email) {
        this.nombreComanda = nombreComanda;
        this.email = email;
    }

    public Comanda(int nombreComanda, Date dataComanda, Date dataDesitjada, Date dataEnviament, Client email) {
        this.nombreComanda = nombreComanda;
        this.dataComanda = dataComanda;
        this.dataDesitjada = dataDesitjada;
        this.dataEnviament = dataEnviament;
        this.email = email;
    }

    public Comanda(int nombreComanda) {
        this.nombreComanda = nombreComanda;
    }  

    public Comanda() {
    }
    

    public int getNombreComanda() {
        return nombreComanda;
    }

    public void setNombreComanda(int nombreComanda) {
        this.nombreComanda = nombreComanda;
    }

    public Date getDataComanda() {
        return dataComanda;
    }

    public void setDataComanda(Date dataComanda) {
        this.dataComanda = dataComanda;
    }

    public Date getDataDesitjada() {
        return dataDesitjada;
    }

    public void setDataDesitjada(Date dataDesitjada) {
        this.dataDesitjada = dataDesitjada;
    }

    public Date getDataEnviament() {
        return dataEnviament;
    }

    public void setDataEnviament(Date dataEnviament) {
        this.dataEnviament = dataEnviament;
    }

    public Client getEmail() {
        return email;
    }

    public void setEmail(Client email) {
        this.email = email;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Comanda{");
        sb.append("nombreComanda=").append(nombreComanda);
        sb.append(", dataComanda=").append(dataComanda);
        sb.append(", dataDesitjada=").append(dataDesitjada);
        sb.append(", dataEnviament=").append(dataEnviament);
        sb.append(", email=").append(email);
        sb.append('}');
        return sb.toString();
    }
    
}
