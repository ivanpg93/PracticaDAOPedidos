/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Comanda;
import model.Producte;

/**
 * Capa lógica de comandes
 *
 * @author noel
 */
public class ComandaLogic extends LogicLayer {
    
    static int comandaOrdre;

    public int getComandaOrdre() {
        return comandaOrdre;
    }

    public void setComandaOrdre(int comandaOrdre) {
        ComandaLogic.comandaOrdre = comandaOrdre;
    }

    public ComandaLogic() throws LogicLayerException {
        super();
    }
    
    
    public Comanda getComanda(int comandaOrdre) throws LogicLayerException
    {
        try {
            Comanda ret = null;
            
            ret = this.getComandaDAO().get(new Comanda(comandaOrdre));
            
            return ret;
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant oficina " + comandaOrdre + " : " + ex.toString());
        }
    }

    /**
     * *
     * Recupera totes les comandes de la taula
     *
     * @return
     * @throws LogicLayerException
     */
    
    public List<Comanda> getAll() throws LogicLayerException {
        List<Comanda> ret = new ArrayList<>();

        try {
            ret = this.getComandaDAO().getAll();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant totes les comandes: " + ex);
        }

        return ret;
    }

    public void save(Comanda c) throws LogicLayerException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (c == null) {
                throw new LogicLayerException("La comanda no es pot afegir a la BBDD");
            }

            // continuem amb el procès
            this.getComandaDAO().save(c);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades afegint la comanda: " + c + " : " + ex);
        }
    }
    
    public void delete(Comanda c) throws LogicLayerException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (c == null) {
                throw new LogicLayerException("La comanda no es pot esborrar de la BBDD");
            }

            // continuem amb el procès
            this.getComandaDAO().delete(c);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades esborrant la comanda: " + c + " : " + ex);
        }
    }
    
    public void update(Comanda c) throws LogicLayerException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (c == null) {
                throw new LogicLayerException("La comanda no es pot editar en la BBDD");
            }

            // continuem amb el procès
            this.getComandaDAO().update(c);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades modificant la comanda: " + c + " : " + ex);
        }
    }

    @Override
    public void close() throws LogicLayerException {
        try {

            if (this.getComandaDAO() != null) {
                System.out.println("Tancant comandaDAO...");
                this.getComandaDAO().close();
            }

        } catch (SQLException ex) {
            throw new LogicLayerException("Error tancant capa logica: " + formatSQLException(ex));
        }
    }
}