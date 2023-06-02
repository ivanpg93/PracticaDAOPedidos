package presentacio;

import aplicacio.App;
import aplicacio.ClientLogic;
import aplicacio.LogicLayerException;
import static dades.DataLayer.con;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author Ivan
 */
public class PrincipalController extends PresentationLayer implements Initializable {

    @FXML
    private void switchToClients() throws IOException {
        App.setRoot("clients");
    }

    @FXML
    private void switchToProductes() throws IOException {
        App.setRoot("productes");
    }

    @FXML
    private void switchToComandes() throws IOException {
        App.setRoot("comandes");
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.clientLogic = new ClientLogic();
            Statement stmt;
            stmt = con.createStatement();
            // Revisem la taula appconfig per si està buida, insertar les dades per defecte de cada propietat abans de iniciar l'aplicació
            String query = ("SELECT * FROM appconfig WHERE defaultCreditLimit IS NOT NULL");
            ResultSet rs = stmt.executeQuery(query);
            if (rs.next()) {
            } else {
                System.out.println("Insertant les dades en la taula appconfig...");
                String consulta = ("INSERT INTO appconfig (defaultCreditLimit, " + "defaultQuantityInStock, "
                        + "defaultQuantityOrdered, " + "defaultProductBenefit, " + "minShippingHours, " + "minCustomerAge, "
                        + "maxLinesPerOrder, " + "maxOrderAmount " + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
                PreparedStatement sentencia = con.prepareStatement(consulta);
                sentencia.setDouble(1, 100.00);
                sentencia.setInt(2, 1);
                sentencia.setInt(3, 1);
                sentencia.setInt(4, 10);
                sentencia.setInt(5, 24);
                sentencia.setInt(6, 18);
                sentencia.setInt(7, 5);
                sentencia.setDouble(8, 2000.00);
                sentencia.execute();
            }
        } catch (LogicLayerException | SQLException ex) {
            Logger.getLogger(PrincipalController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
