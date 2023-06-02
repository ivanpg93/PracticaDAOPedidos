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

/**
 *
 * @author ivan
 */
public class ComandaDAO extends DataLayer implements DAOInterface<Comanda> {

    public ComandaDAO() throws SQLException {
        super();
    }

    @Override
    public List<Comanda> getAll() throws SQLException {
        List<Comanda> ret = new ArrayList<>();

        // comanda SQL
        String consulta = "SELECT * FROM orders";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();

        //mostrem resultats via nom del camp
        while (resultats.next()) {
            Comanda c = new Comanda();

            c.setNombreComanda(resultats.getInt("orderNumber"));
            c.setDataComanda(resultats.getDate("orderDate"));
            c.setDataDesitjada(resultats.getDate("requiredDate"));
            c.setDataEnviament(resultats.getDate("shippedDate"));
            c.setEmail((Client) resultats.getObject("customers_customerEmail"));

            ret.add(c);
        }
        return ret;
    }

    @Override
    public void save(Comanda t) throws SQLException {
        // comanda SQL
        String consulta = ("INSERT INTO orders (orderNumber, " + "orderDate, "
                + "requiredDate, " + "shippedDate, " + "customers_customerEmail "
                + ") VALUES (?, ?, ?, ?, ?)");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setInt(1, t.getNombreComanda());
        sentencia.setDate(2, t.getDataComanda());
        sentencia.setDate(3, t.getDataDesitjada());
        sentencia.setDate(4, t.getDataEnviament());
        sentencia.setString(5, t.getEmail().getEmail());

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public void update(Comanda t) throws SQLException {
// comanda SQL
        String consulta = ("UPDATE orders SET orderNumber = (?), orderDate = (?), "
                + "requiredDate = (?), shippedDate = (?), customers_customerEmail = (?)"
                + " WHERE orderNumber = ?");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setInt(1, t.getNombreComanda());
        sentencia.setDate(2, t.getDataComanda());
        sentencia.setDate(3, t.getDataDesitjada());
        sentencia.setDate(4, t.getDataEnviament());
        sentencia.setString(5, t.getEmail().getEmail());       

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public void delete(Comanda t) throws SQLException {
// comanda SQL
        String consulta = ("DELETE FROM orders WHERE orderNumber = ?");
        
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        
        sentencia.setInt(1, t.getNombreComanda());

        // execució de consulta
        sentencia.executeUpdate();    }

    @Override
    public Comanda get(Comanda t) throws SQLException {

        Comanda ret = null;
        String consulta = "SELECT * FROM orders WHERE orderNumber = ?";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        // substitució de valors per ordre
        sentencia.setInt(1, t.getNombreComanda());

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();

        //mostrem resultats via nom del camp
        if (resultats.next()) {
            int nombreComanda = resultats.getInt("orderNumber");
            Date dataComanda = resultats.getDate("orderDate");
            Date dataDesitjada = resultats.getDate("requiredDate");
            Date dataEnviament = resultats.getDate("shippedDate");
            String email = resultats.getString("customers_customerEmail");

            // Conseguir cliente a partir del email llamando a ClientDao
            Client client = new Client(email);
            client = new ClientDAO().get(client);
            ret = new Comanda(nombreComanda, dataComanda, dataDesitjada, dataEnviament, client);
        }

        return ret;
    }

}
