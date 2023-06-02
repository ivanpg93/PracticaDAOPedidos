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
public class EditProducteController extends PresentationLayer implements Initializable {

    int codiProducte;
    Producte p;
    Alerts alert = new Alerts();

    @FXML
    public TextField txtNom, txtDescripcio, txtStock, txtPreu;

    @FXML
    private void switchToProductes() throws IOException {
        App.setRoot("productes");
    }

    @FXML
    // Setegem els txt amb els valors del Producte
    private void recuperarDatos() {
        txtNom.setText(p.getNomProducte());
        txtDescripcio.setText(p.getDescripcioProducte());
        txtStock.setText(String.valueOf(p.getStock()));
        txtPreu.setText(String.valueOf(p.getPreu()));
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
    // Editar els valors del Producte en la BBDD amb les dades del formulari
    private void editarProducte(ActionEvent event) throws IOException, LogicLayerException {
        // Revisem si tot el formulari està emplenat
        if (txtNom.getText() == "" || txtDescripcio.getText() == "" || txtStock.getText() == "" || txtPreu.getText() == "") {
            alert.mostrarAlertOmplirFormulari(event);
        } else {
            // Modifiquem els valors del objecte Producte segons les dades del formulari
            p.setNomProducte(txtNom.getText());
            p.setDescripcioProducte(txtDescripcio.getText());
            p.setStock(Integer.valueOf(txtStock.getText()));
            p.setPreu(Double.valueOf(txtPreu.getText()));
            this.producteLogic.editarProducte(p);
            switchToProductes();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.producteLogic = new ProducteLogic();
            // Agafem els valors del Producte a modificar
            codiProducte = this.producteLogic.getCodiProducte();
            p = this.producteLogic.getProducte(codiProducte);
            recuperarDatos();
        } catch (LogicLayerException ex) {
            Logger.getLogger(ProductesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
