/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Ivan
 */
public class LineaComanda {
    
    Comanda nombreComanda;
    Producte codeProducte;
    int quantitat;
    double preuUnitat;
    int nombreLineaComanda;

    public LineaComanda(Comanda nombreComanda, Producte codeProducte) {
        this.nombreComanda = nombreComanda;
        this.codeProducte = codeProducte;
    }
    
    public LineaComanda(Comanda nombreComanda, Producte codeProducte, int quantitat, double preuUnitat, int nombreLineaComanda) {
        this.nombreComanda = nombreComanda;
        this.codeProducte = codeProducte;
        this.quantitat = quantitat;
        this.preuUnitat = preuUnitat;
        this.nombreLineaComanda = nombreLineaComanda;
    }

    public LineaComanda() {
    }

    public Comanda getNombreComanda() {
        return nombreComanda;
    }

    public void setNombreComanda(Comanda nombreComanda) {
        this.nombreComanda = nombreComanda;
    }

    public Producte getCodeProducte() {
        return codeProducte;
    }

    public void setCodeProducte(Producte codeProducte) {
        this.codeProducte = codeProducte;
    }

    public int getQuantitat() {
        return quantitat;
    }

    public void setQuantitat(int quantitat) {
        this.quantitat = quantitat;
    }

    public double getPreuUnitat() {
        return preuUnitat;
    }

    public void setPreuUnitat(double preuUnitat) {
        this.preuUnitat = preuUnitat;
    }

    public int getNombreLineaComanda() {
        return nombreLineaComanda;
    }

    public void setNombreLineaComanda(int nombreLineaComanda) {
        this.nombreLineaComanda = nombreLineaComanda;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LineaComanda{");
        sb.append("nombreComanda=").append(nombreComanda);
        sb.append(", codeProducte=").append(codeProducte);
        sb.append(", quantitat=").append(quantitat);
        sb.append(", preuUnitat=").append(preuUnitat);
        sb.append(", nombreLineaComanda=").append(nombreLineaComanda);
        sb.append('}');
        return sb.toString();
    }
    
}
