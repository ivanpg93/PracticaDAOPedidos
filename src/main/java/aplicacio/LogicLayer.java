/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aplicacio;

import dades.ClientDAO;
import dades.ComandaDAO;
import dades.LiniaComandaDAO;
import dades.ProducteDAO;
import java.sql.SQLException;

/**
 *
 * @author Ivan
 */
public abstract class LogicLayer {

    private dades.ClientDAO clientDAO;
    private dades.ProducteDAO producteDAO;
    private dades.ComandaDAO comandaDAO;
    private dades.LiniaComandaDAO liniaComandaDAO;

    /**
     * *
     * Connecta amb la capa de dades i obre connexió
     *
     * @throws aplicacio.LogicLayerException
     */
    public LogicLayer() throws LogicLayerException {

        try {

            // inicialitzem capa dades
            this.clientDAO = new ClientDAO();
            this.producteDAO = new ProducteDAO();
            this.comandaDAO = new ComandaDAO();
            this.liniaComandaDAO = new LiniaComandaDAO();

        } catch (SQLException ex) {

            throw new LogicLayerException("Error inicialitzant capa de dades: " + formatSQLException(ex));
        }
    }

    public String formatSQLException(SQLException ex) {
        String ret = "";

        ret += "SQLException : " + ex.getMessage() + System.lineSeparator();
        ret += "SQLState     : " + ex.getSQLState() + System.lineSeparator();
        ret += "VendorCode   : " + ex.getErrorCode() + System.lineSeparator();
        ret += "LocalMessage : " + ex.getLocalizedMessage() + System.lineSeparator();

        return ret;
    }

    public ClientDAO getClientDAO() {
        return clientDAO;
    }
    
    public ProducteDAO getProducteDAO() {
        return producteDAO;
    }
    
    public ComandaDAO getComandaDAO() {
        return comandaDAO;
    }
    
    public LiniaComandaDAO getLiniaComandaDAO() {
        return liniaComandaDAO;
    }

    /**
     * *
     * Permet a les subclasses reimplementar aquest mètode A diferència d'una
     * interfície, aquesta reimplementació no és obligatòria
     *
     * @throws LogicLayerException
     */
    public abstract void close() throws LogicLayerException;
}
