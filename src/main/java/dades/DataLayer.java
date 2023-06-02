/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dades;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 
 * @author Ivan
 */
public abstract class DataLayer {
    
    public static Connection con;

    public DataLayer() throws SQLException {
        
        //intentem connectar
        this.con = MySQLConnector.ConnectarBD("m03uf6_22_23", "root", "1234");
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection _con) {
        con = _con;
    }
    
    /***
     * Tanca la connexió del DAO corresponent
     * @throws java.sql.SQLException
     */
    public void close() throws SQLException
    {
        if (con != null)
        {
            System.out.println("Tancant connexio: " + this.con.getMetaData().getURL());
            this.con.close();
        }
    }
            
}
