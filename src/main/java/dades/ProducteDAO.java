/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Producte;

/**
 *
 * @author Ivan
 */
public class ProducteDAO extends DataLayer implements DAOInterface<Producte> {

    public ProducteDAO() throws SQLException {
        super();
    }

    @Override
    public List<Producte> getAll() throws SQLException {
        List<Producte> ret = new ArrayList<>();
        
        // comanda SQL
        String consulta = "select * from products";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
        
        /*int affectedRows = sentencia.executeUpdate();
        if(affectedRows == 0) {
            throw new SQLException("No s'ha pogut guardar");
        }
        
        ResultSet generatedKeys = sentencia.getGeneratedKeys();
        if(generatedKeys.next()) {
            int idGenerado = generatedKeys.getInt(1);
        }*/

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();

        //mostrem resultats via nom del camp
        while (resultats.next())
        {
            Producte p = new Producte();

            p.setCodeProducte(Integer.parseInt(resultats.getString("productCode")));
            p.setNomProducte(resultats.getString("productName"));
            p.setDescripcioProducte(resultats.getString("productDescription"));
            p.setStock(resultats.getInt("quantityInStock"));
            p.setPreu(resultats.getDouble("buyPrice"));

            ret.add(p);
        }
        
        return ret;
    }
    
    public void afegirProducte(Producte p) throws SQLException {
        
        // comanda SQL
        String consulta = ("INSERT INTO products (productName, " + "productDescription, " +
                "quantityInStock, " + "buyPrice) VALUES (?, ?, ?, ?)");
        
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta, Statement.RETURN_GENERATED_KEYS);
        
        /*int affectedRows = sentencia.executeUpdate();
        if(affectedRows == 0) {
            throw new SQLException("No s'ha pogut guardar");
        }
        
        ResultSet generatedKeys = sentencia.getGeneratedKeys();
        if(generatedKeys.next()) {
            int idGenerado = generatedKeys.getInt(1);
        }*/
        
        sentencia.setString(1, p.getNomProducte());
        sentencia.setString(2, p.getDescripcioProducte());
        sentencia.setString(3, Integer.toString(p.getStock()));
        sentencia.setString(4, Double.toString(p.getPreu()));

        // execució de consulta
        sentencia.executeUpdate();
    }
    
    public void editarProducte(Producte p) throws SQLException {
        // comanda SQL
        String consulta = ("UPDATE products SET productName = (?), productDescription = (?), "
                + "quantityInStock = (?), buyPrice = (?)"
                + " WHERE productCode = ?");
        
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        
        sentencia.setString(1, p.getNomProducte());
        sentencia.setString(2, p.getDescripcioProducte());
        sentencia.setString(3, Integer.toString(p.getStock()));
        sentencia.setString(4, Double.toString(p.getPreu()));
        sentencia.setString(5, Integer.toString(p.getCodeProducte()));

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public void save(Producte t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Producte t) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Producte p) throws SQLException {
        // comanda SQL
        String consulta = ("DELETE FROM products WHERE productName = ?");
        
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        
        sentencia.setString(1, p.getNomProducte());

        // execució de consulta
        sentencia.executeUpdate();
    }

    @Override
    public Producte get(Producte p) throws SQLException {
        Producte ret = null;
        
        // comanda SQL
        String consulta = "SELECT * FROM products WHERE productCode = ?";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
            
        // substitució de valors per ordre
        sentencia.setInt(1, p.getCodeProducte());

        // execució de consulta
        ResultSet resultats = sentencia.executeQuery();  

        //mostrem resultats via nom del camp
        if (resultats.next())
        {
            Producte pr = new Producte();

            pr.setCodeProducte(Integer.parseInt(resultats.getString("productCode")));
            pr.setNomProducte(resultats.getString("productName"));
            pr.setDescripcioProducte(resultats.getString("productDescription"));
            pr.setStock(resultats.getInt("quantityInStock"));
            pr.setPreu(resultats.getDouble("buyPrice"));
            
            ret = pr;
        }
        
        return ret;
    }
    
    public int getDefaultQuantityInStock() throws SQLException {
        int ret = 0;
        String consulta = "SELECT defaultQuantityInStock FROM appconfig";
        PreparedStatement sentencia = this.getCon().prepareStatement(consulta);
        ResultSet resultats = sentencia.executeQuery();
        if (resultats.next()) {
            ret = resultats.getInt("defaultQuantityInStock");
        }
        return ret;
    }
}
