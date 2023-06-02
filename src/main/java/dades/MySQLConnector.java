/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Ivan
 */
public class MySQLConnector {
    
     /**
     * Connecta a una BD mysql 
     * @param bd
     * @param usuari
     * @param password
     * @return objecte Connection
     * @throws SQLException 
     */
    public static Connection ConnectarBD(String bd, String usuari, String password) throws SQLException
    {
        Connection ret = null;
        
         ret =  DriverManager.getConnection("jdbc:mysql://localhost:3306/"+bd+"?useUnicode=true&"
                            + "useJDBCCompliantTimezoneShift=true&"
                            + "useLegacyDatetimeCode=false&serverTimezone=UTC", usuari, password);
        
        return ret;
    }
    
}
