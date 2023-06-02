/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dades;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import model.Client;
import model.Comanda;
import model.LineaComanda;
import model.Producte;

/**
 *
 * @author ivan
 */
public class LiniaComandaDAO extends DataLayer implements DAOInterface<LineaComanda> {

    public LiniaComandaDAO() throws SQLException {
        super();
    }

    @Override
    public List<LineaComanda> getAll() throws SQLException {
        List<LineaComanda> ret = new ArrayList<>();

        // comanda SQL
        String consulta = "SELECT * FROM orderdetails";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();

        //mostrem resultats via nom del camp
        while (resultats.next()) {
            LineaComanda c = new LineaComanda();

            c.setNombreComanda((Comanda) resultats.getObject("orderNumber"));
            c.setCodeProducte((Producte) resultats.getObject("producteCode"));
            c.setQuantitat(resultats.getInt("quantityOrdered"));
            c.setPreuUnitat(resultats.getDouble("priceEach"));
            c.setNombreLineaComanda(resultats.getInt("orderLineNumber"));

            ret.add(c);
        }

        return ret;
    }

    @Override
    public void save(LineaComanda t) throws SQLException {
        // comanda SQL
        String consulta = ("INSERT INTO orderdetails (orderNumber, " + "productCode, "
                + "quantityOrdered, " + "priceEach, " + "orderLineNumber"
                + ") VALUES (?, ?, ?, ?, ?)");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setInt(1, t.getNombreComanda().getNombreComanda());
        sentencia.setInt(2, t.getCodeProducte().getCodeProducte());
        sentencia.setInt(3, t.getQuantitat());
        sentencia.setDouble(4, t.getPreuUnitat());
        sentencia.setInt(5, t.getNombreLineaComanda());

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public void update(LineaComanda t) throws SQLException {
        // comanda SQL
        String consulta = ("UPDATE orderdetails SET orderNumber = (?), productCode = (?), "
                + "quantityOrdered = (?), priceEach = (?), orderLineNumber = (?)"
                + " WHERE orderNumber = ?");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setInt(1, t.getNombreComanda().getNombreComanda());
        sentencia.setInt(2, t.getCodeProducte().getCodeProducte());
        sentencia.setInt(3, t.getQuantitat());
        sentencia.setDouble(4, t.getPreuUnitat());
        sentencia.setInt(5, t.getNombreLineaComanda());

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public void delete(LineaComanda t) throws SQLException {
// comanda SQL
        String consulta = ("DELETE FROM orderdetails WHERE orderNumber = ?");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setInt(1, t.getNombreComanda().getNombreComanda());

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public LineaComanda get(LineaComanda t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int getNextId(Comanda comanda) throws SQLException {
        int ret = 1;

        // comanda SQL
        String consulta = "SELECT MAX(orderLineNumber) FROM orderdetails WHERE orderNumber = ?";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        // substitució de valors per ordre
        sentencia.setInt(1, comanda.getNombreComanda());

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();

        //mostrem resultats via nom del camp
        if (resultats.next()) {
            ret = resultats.getInt(1) + 1;
        }
        return ret;
    }

}
