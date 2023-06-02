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
public class Client {

    String email;
    String dni;
    String nom;
    String telefon;
    double limitCredit;
    Date aniversari;

    public Client() {

    }
    
    public Client(String email) {
        this.email = email;
    }

    public Client(String email, String dni, String nom, String telefon, double limitCredit, Date aniversari) {
        this.email = email;
        this.dni = dni;
        this.nom = nom;
        this.telefon = telefon;
        this.limitCredit = limitCredit;
        this.aniversari = aniversari;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public double getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(double limitCredit) {
        this.limitCredit = limitCredit;
    }

    public Date getAniversari() {
        return aniversari;
    }

    public void setAniversari(Date aniversari) {
        this.aniversari = aniversari;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Client{");
        sb.append("email=").append(email);
        sb.append(", dni=").append(dni);
        sb.append(", nom=").append(nom);
        sb.append(", telefon=").append(telefon);
        sb.append(", limitCredit=").append(limitCredit);
        sb.append(", aniversari=").append(aniversari);
        sb.append('}');
        return sb.toString();
    }

}
