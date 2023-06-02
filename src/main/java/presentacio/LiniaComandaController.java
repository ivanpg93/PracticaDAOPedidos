package presentacio;

import aplicacio.App;
import aplicacio.ComandaLogic;
import aplicacio.LiniaComandaLogic;
import aplicacio.LogicLayer;
import aplicacio.LogicLayerException;
import aplicacio.ProducteLogic;
import dades.ComandaDAO;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Comanda;
import model.LineaComanda;
import model.Producte;

public class LiniaComandaController extends PresentationLayer implements Initializable {

    @FXML
    TextField txtQuantitat, txtPreu, txtNumero, txtProducte;

    @FXML
    void crearLinia() {
        guardarLinia();
    }

    @FXML
    void cancelarLinia() {

    }

    private void guardarLinia() {
        try {
            Comanda comanda = comandaLogic.getComanda(Integer.parseInt(txtNumero.getText()));
            Producte codeProducte = producteLogic.getProducte(Integer.parseInt(txtProducte.getText()));
            int quantitat = Integer.parseInt(txtQuantitat.getText());
            double preuUnitat = Double.parseDouble(txtPreu.getText());
            int nombreLiniaComanda = liniaComandaLogic.getNextId(comanda);

            LineaComanda linia = new LineaComanda(comanda, codeProducte, quantitat, preuUnitat, nombreLiniaComanda);
            liniaComandaLogic.afegirLiniaComanda(linia);
        } catch (Exception e) {
            System.out.println("L'exepcio es: " + e.getMessage());
        }

    }

    @Override
    public void close() {
        try {
            if (this.comandaLogic != null) {
                System.out.println("Tancant comandaController...");
                this.comandaLogic.close();
            }

        } catch (LogicLayerException ex) {
            System.out.println("No s'ha pogut finalitzar correctament el controller: " + ex);
        }
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.comandaLogic = new ComandaLogic();
            this.producteLogic = new ProducteLogic();
            this.liniaComandaLogic = new LiniaComandaLogic();
        } catch (LogicLayerException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
