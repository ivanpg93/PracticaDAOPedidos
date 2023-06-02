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

/**
 *
 * @author Ivan
 */
public class ClientDAO extends DataLayer implements DAOInterface<Client> {

    public ClientDAO() throws SQLException {
        super();
    }

    @Override
    public List<Client> getAll() throws SQLException {
        List<Client> ret = new ArrayList<>();

        // comanda SQL
        String consulta = "SELECT * FROM customers";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();

        //mostrem resultats via nom del camp
        while (resultats.next()) {
            Client c = new Client();

            c.setEmail(resultats.getString("customerEmail"));
            c.setDni(resultats.getString("idCard"));
            c.setNom(resultats.getString("customerName"));
            c.setTelefon(resultats.getString("phone"));
            c.setLimitCredit(resultats.getDouble("creditLimit"));
            c.setAniversari(resultats.getDate("birthDate"));

            ret.add(c);
        }

        return ret;
    }

    public void afegirClient(Client c) throws SQLException, ParseException {

        // comanda SQL
        String consulta = ("INSERT INTO customers (customerEmail, " + "idCard, "
                + "customerName, " + "phone, " + "creditLimit, " + "birthDate "
                + ") VALUES (?, ?, ?, ?, ?, ?)");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setString(1, c.getEmail());
        sentencia.setString(2, c.getDni());
        sentencia.setString(3, c.getNom());
        sentencia.setString(4, c.getTelefon());
        sentencia.setString(5, Double.toString(c.getLimitCredit()));

        // Formateamos la data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(c.getAniversari().toString());
        java.sql.Date sqlDate = new Date(date.getTime());
        sentencia.setString(6, sqlDate.toString());

        // execució de consulta
        sentencia.executeUpdate();
    }

    public void editarClient(Client c) throws SQLException, ParseException {
        // comanda SQL
        String consulta = ("UPDATE customers SET customerEmail = (?), idCard = (?), "
                + "customerName = (?), phone = (?), creditLimit = (?), birthDate = (?)"
                + " WHERE customerEmail = ?");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setString(1, c.getEmail());
        sentencia.setString(2, c.getDni());
        sentencia.setString(3, c.getNom());
        sentencia.setString(4, c.getTelefon());
        sentencia.setString(5, Double.toString(c.getLimitCredit()));

        // Formateamos la data
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date date = sdf.parse(c.getAniversari().toString());
        java.sql.Date sqlDate = new Date(date.getTime());
        sentencia.setString(6, sqlDate.toString());

        sentencia.setString(7, c.getEmail());

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public void save(Client t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Client t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Client c) throws SQLException {
        // comanda SQL
        String consulta = ("DELETE FROM customers WHERE customerEmail = ?");

        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        sentencia.setString(1, c.getEmail());

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public Client get(Client c) throws SQLException {
        Client ret = null;

        // comanda SQL
        String consulta = "SELECT * FROM customers WHERE customerEmail = ?";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);

        // substitució de valors per ordre
        sentencia.setString(1, c.getEmail());

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();

        //mostrem resultats via nom del camp
        if (resultats.next()) {
            Client cl = new Client();

            cl.setEmail(resultats.getString("customerEmail"));
            cl.setDni(resultats.getString("idCard"));
            cl.setNom(resultats.getString("customerName"));
            cl.setTelefon(resultats.getString("phone"));
            cl.setLimitCredit(resultats.getDouble("creditLimit"));
            cl.setAniversari(resultats.getDate("birthDate"));

            ret = cl;
        }

        return ret;
    }

    public Double getDefaultCreditLimit() throws SQLException {
        Double ret = null;
        String consulta = "SELECT defaultCreditLimit FROM appconfig";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        ResultSet resultats = sentencia.executeQuery();
        if (resultats.next()) {
            ret = resultats.getDouble("defaultCreditLimit");
        }
        return ret;
    }
    
    public int getMinCustomerAge() throws SQLException {
        int ret = 0;
        String consulta = "SELECT minCustomerAge FROM appconfig";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        ResultSet resultats = sentencia.executeQuery();
        if (resultats.next()) {
            ret = resultats.getInt("minCustomerAge");
        }
        return ret;
    }
}
