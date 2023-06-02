/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacio;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import model.Client;
import model.Comanda;
import model.LineaComanda;
import model.Producte;

/**
 * Capa lógica de comandes
 *
 * @author noel
 */
public class LiniaComandaLogic extends LogicLayer {

    static String liniaComandaLogic;

    public String getLiniaComandaLogic() {
        return liniaComandaLogic;
    }

    public void setLiniaComandaLogic(String liniaComandaLogic) {
        LiniaComandaLogic.liniaComandaLogic = liniaComandaLogic;
    }

    public LineaComanda getComanda(Comanda nombreComanda, Producte codeComanda) throws LogicLayerException {
        try {
            LineaComanda ret = null;

            ret = this.getLiniaComandaDAO().get(new LineaComanda(nombreComanda, codeComanda));

            return ret;
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades recuperant oficina " + nombreComanda + " " + codeComanda + " : " + ex.toString());
        }
    }
    
        public List<LineaComanda> getAll() throws LogicLayerException {
        List<LineaComanda> ret = new ArrayList<>();

        try {
            ret = this.getLiniaComandaDAO().getAll();
        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa");
        }

        return ret;
    }

    public void afegirLiniaComanda(LineaComanda c) throws LogicLayerException, ParseException {
        try {

            //verifiquem si oficina realment existeix. Si no, salta una excepció.
            if (c == null) {
                throw new LogicLayerException("El client no es pot afegir a la BBDD");
            }

            // continuem amb el procès
            this.getLiniaComandaDAO().save(c);

        } catch (SQLException ex) {
            throw new LogicLayerException("Error de capa de dades afegint el client: " + c + " : " + ex);
        }
    }

    public LiniaComandaLogic() throws LogicLayerException {
        super();
    }

    public int getNextId(Comanda comanda) throws SQLException {
        int nextId = this.getLiniaComandaDAO().getNextId(comanda);
        return nextId;
    }

    @Override
    public void close() throws LogicLayerException {
        try {

            if (this.getLiniaComandaDAO() != null) {
                System.out.println("Tancant liniaComandaDAO...");
                this.getLiniaComandaDAO().close();
            }

        } catch (SQLException ex) {
            throw new LogicLayerException("Error tancant capa logica: " + formatSQLException(ex));
        }
    }

}
