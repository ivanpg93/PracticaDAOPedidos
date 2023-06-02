/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ivan
 */
public class Producte {

    int codeProducte;
    String nomProducte;
    String descripcioProducte;
    int stock;
    double preu;
    
    public Producte() {
        
    }
    
    public Producte(int codeProducte) {
        this.codeProducte = codeProducte;
    }

    public Producte(String nomProducte, String descripcioProducte, int stock, double preu) {
        //this.codeProducte = codeProducte;
        this.nomProducte = nomProducte;
        this.descripcioProducte = descripcioProducte;
        this.stock = stock;
        this.preu = preu;
    }

    public int getCodeProducte() {
        return codeProducte;
    }

    public void setCodeProducte(int codeProducte) {
        this.codeProducte = codeProducte;
    }

    public String getNomProducte() {
        return nomProducte;
    }

    public void setNomProducte(String nomProducte) {
        this.nomProducte = nomProducte;
    }

    public String getDescripcioProducte() {
        return descripcioProducte;
    }

    public void setDescripcioProducte(String descripcioProducte) {
        this.descripcioProducte = descripcioProducte;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPreu() {
        return preu;
    }

    public void setPreu(double preu) {
        this.preu = preu;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Producte{");
        sb.append("codeProducte=").append(codeProducte);
        sb.append(", nomProducte=").append(nomProducte);
        sb.append(", descripcioProducte=").append(descripcioProducte);
        sb.append(", stock=").append(stock);
        sb.append(", preu=").append(preu);
        sb.append('}');
        return sb.toString();
    }

}
