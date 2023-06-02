package presentacio;

import aplicacio.App;
import aplicacio.LogicLayerException;
import aplicacio.ProducteLogic;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Producte;
import utils.Alerts;

/**
 *
 * @author Ivan
 */
public class ProductesController extends PresentationLayer implements Initializable {

    ObservableList<Producte> llistaObservableProductes;
    Alerts alert = new Alerts();

    @FXML
    TableView tv_1;

    @FXML
    TableColumn colCodeProducte, colNomProducte, colDescripcioProducte, colStock, colPreu;

    @FXML
    private void switchToClients() throws IOException {
        App.setRoot("clients");
    }

    @FXML
    private void switchToComandes() throws IOException {
        App.setRoot("comandes");
    }

    @FXML
    private void switchToAddProducte() throws IOException {
        App.setRoot("nouProducte");
    }

    @FXML
    private void switchToEditProducte() throws IOException {
        App.setRoot("editarProducte");
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
    // Esborrar el Producte de la BBDD i de la ObservableList
    private void deleteProducte(ActionEvent event) throws LogicLayerException {
        Producte p = (Producte) tv_1.getSelectionModel().getSelectedItem();
        if (p == null) {
            alert.mostrarAlertItemNoSelected(event);
        } else {
            this.producteLogic.deleteProducte(p);
            llistaObservableProductes.remove(p);
        }
    }

    @FXML
    // Anar a la pantalla per editar el Producte, agafant els seus valors
    private void actualizarProducteEditar(ActionEvent event) throws LogicLayerException, IOException {
        Producte p = (Producte) tv_1.getSelectionModel().getSelectedItem();
        if (p == null) {
            alert.mostrarAlertItemNoSelected(event);
        } else {
            this.producteLogic.setCodiProducte(p.getCodeProducte());
            switchToEditProducte();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llistaObservableProductes = FXCollections.observableArrayList();
        try {
            this.producteLogic = new ProducteLogic();
            List<Producte> productes;
            productes = producteLogic.getAll();
            //definim una llista observable d'objectes de la classe Producte
            productes.forEach(p -> llistaObservableProductes.add(p));
        } catch (LogicLayerException ex) {
            Logger.getLogger(ProductesController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * *
         * Establim un vincle entre els atributs de l'objecte Producte i cada
         * columna del tableview:
         *
         * P.ex: colNomProducte <---> "nom" Ull!! NO són els noms de les
         * columnes, sino allò que retorna el getter sense el "get" inicial.
         */
        colCodeProducte.setCellValueFactory(new PropertyValueFactory<>("codeProducte"));
        colNomProducte.setCellValueFactory(new PropertyValueFactory<>("nomProducte"));
        colDescripcioProducte.setCellValueFactory(new PropertyValueFactory<>("descripcioProducte"));
        colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));
        colPreu.setCellValueFactory(new PropertyValueFactory<>("preu"));

        tv_1.setItems(llistaObservableProductes);
    }
}
