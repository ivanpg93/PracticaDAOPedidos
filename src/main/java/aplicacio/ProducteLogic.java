/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacio;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Producte;

/**
 * Capa lógica de productes
 *
 * @author Ivan
 */
public class ProducteLogic extends LogicLayer {
    
    static int codiProducte;

    public int getCodiProducte() {
        return codiProducte;
    }

    public void setCodiProducte(int codiProducte) {
        ProducteLogic.codiProducte = codiProducte;
    }

    public ProducteLogic() throws LogicLayerException {
        super();
    }
    
    public Producte getProducte(int codiProducte) throws LogicLayerException
    {
        try {
            Producte ret = null;
            
            ret = this.getProducteDAO().get(new Producte(codiProducte));
            
            return ret;
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant oficina " + codiProducte + " : " + ex.toString());
        }
    }

    /**
     * *
     * Recupera tots els productes de la taula
     *
     * @return
     * @throws LogicLayerException
     */
    public List<Producte> getAll() throws LogicLayerException {
        List<Producte> ret = new ArrayList<>();

        try {
            ret = this.getProducteDAO().getAll();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant tots els productes: " + ex);
        }

        return ret;
    }
    
    public int getDefaultQuantityInStock() throws LogicLayerException {
        int ret;

        try {
            ret = this.getProducteDAO().getDefaultQuantityInStock();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades el stock per defecte dels productes: " + ex);
        }

        return ret;
    }

    public void afegirProducte(Producte p) throws LogicLayerException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (p == null) {
                throw new LogicLayerException("El producte no es pot afegir a la BBDD");
            }

            // continuem amb el procès
            this.getProducteDAO().afegirProducte(p);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades afegint el producte: " + p + " : " + ex);
        }
    }
    
    public void deleteProducte(Producte p) throws LogicLayerException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (p == null) {
                throw new LogicLayerException("El producte no es pot esborrar de la BBDD");
            }

            // continuem amb el procès
            this.getProducteDAO().delete(p);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades esborrant el producte: " + p + " : " + ex);
        }
    }
    
    public void editarProducte(Producte p) throws LogicLayerException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (p == null) {
                throw new LogicLayerException("El producte no es pot editar en la BBDD");
            }

            // continuem amb el procès
            this.getProducteDAO().editarProducte(p);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades modificant el producte: " + p + " : " + ex);
        }
    }

    @Override
    public void close() throws LogicLayerException {
        try {

            if (this.getProducteDAO() != null) {
                System.out.println("Tancant producteDAO...");
                this.getProducteDAO().close();
            }

        } catch (SQLException ex) {
            throw new LogicLayerException("Error tancant capa logica: " + formatSQLException(ex));
        }
    }
}