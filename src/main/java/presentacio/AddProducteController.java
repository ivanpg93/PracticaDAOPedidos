package presentacio;

import aplicacio.App;
import aplicacio.LogicLayerException;
import aplicacio.ProducteLogic;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Producte;
import utils.Alerts;

/**
 *
 * @author Ivan
 */
public class AddProducteController extends PresentationLayer implements Initializable {

    Producte p = new Producte();
    Alerts alert = new Alerts();

    @FXML
    public TextField txtNom, txtDescripcio, txtStock, txtPreu;

    @FXML
    private void switchToProductes() throws IOException {
        App.setRoot("productes");
    }

    @Override
    public void close() {
        try {
            if (this.producteLogic != null) {
                System.out.println("Tancant producteController...");
                this.producteLogic.close();
            }

        } catch (LogicLayerException ex) {
            System.out.println("No s'ha pogut finalitzar correctament el controller: " + ex);
        }
    }

    @FXML
    // Crear el Producte en la BBDD amb les dades del formulari
    private void crearProducte(ActionEvent event) throws IOException, LogicLayerException {
        // Revisem si tot el formulari està emplenat
        if (txtNom.getText() == "" || txtDescripcio.getText() == "" || txtStock.getText() == "" || txtPreu.getText() == "") {
            alert.mostrarAlertOmplirFormulari(event);
        } else {
            // Modifiquem els valors del objecte Producte segons les dades del formulari
            p.setNomProducte(txtNom.getText());
            p.setDescripcioProducte(txtDescripcio.getText());
            p.setStock(Integer.valueOf(txtStock.getText()));
            p.setPreu(Double.valueOf(txtPreu.getText()));
            this.producteLogic.afegirProducte(p);
            switchToProductes();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.producteLogic = new ProducteLogic();
            // Agafem el valor per defecte del stock dels Productes
            txtStock.setText(String.valueOf(this.producteLogic.getDefaultQuantityInStock()));
        } catch (LogicLayerException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
