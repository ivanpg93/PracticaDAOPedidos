package presentacio;

import aplicacio.App;
import aplicacio.ClientLogic;
import aplicacio.LogicLayerException;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
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
import model.Client;
import utils.Alerts;

/**
 *
 * @author Ivan
 */
public class ClientsController extends PresentationLayer implements Initializable {

    ObservableList<Client> llistaObservableClients;
    Alerts alert = new Alerts();

    @FXML
    TableView tv_1;

    @FXML
    TableColumn colEmail, colDni, colNom, colTelefon, colLimitCredit, colAniversari;

    @FXML
    private void switchToProductes() throws IOException {
        App.setRoot("productes");
    }

    @FXML
    private void switchToComandes() throws IOException {
        App.setRoot("comandes");
    }

    @FXML
    private void switchToAddClient() throws IOException {
        App.setRoot("nouClient");
    }

    @FXML
    private void switchToEditClient() throws IOException {
        App.setRoot("editarClient");
    }

    @Override
    public void close() {
        try {
            if (this.clientLogic != null) {
                System.out.println("Tancant clientController...");
                this.clientLogic.close();
            }

        } catch (LogicLayerException ex) {
            System.out.println("No s'ha pogut finalitzar correctament el controller: " + ex);
        }
    }

    @FXML
    // Esborrar el Client de la BBDD i de la ObservableList
    private void deleteClient(ActionEvent event) throws LogicLayerException, ParseException {
        Client c = (Client) tv_1.getSelectionModel().getSelectedItem();
        if (c == null) {
            alert.mostrarAlertItemNoSelected(event);
        } else {
            this.clientLogic.deleteClient(c);
            llistaObservableClients.remove(c);
        }
    }

    @FXML
    // Anar a la pantalla per editar el Client, agafant els seus valors
    private void actualizarClientEditar(ActionEvent event) throws LogicLayerException, ParseException, IOException {
        Client c = (Client) tv_1.getSelectionModel().getSelectedItem();
        if (c == null) {
            alert.mostrarAlertItemNoSelected(event);
        } else {
            this.clientLogic.setEmailClient(c.getEmail());
            switchToEditClient();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        llistaObservableClients = FXCollections.observableArrayList();
        try {
            this.clientLogic = new ClientLogic();
            List<Client> clients;
            clients = clientLogic.getAll();
            //definim una llista observable d'objectes de la classe Clients
            clients.forEach(c -> llistaObservableClients.add(c));
        } catch (LogicLayerException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        /**
         * *
         * Establim un vincle entre els atributs de l'objecte Client i cada
         * columna del tableview:
         *
         * P.ex: colEmail <---> "email" Ull!! NO són els noms de les columnes,
         * sino allò que retorna el getter sense el "get" inicial.
         */
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colTelefon.setCellValueFactory(new PropertyValueFactory<>("telefon"));
        colLimitCredit.setCellValueFactory(new PropertyValueFactory<>("limitCredit"));
        colAniversari.setCellValueFactory(new PropertyValueFactory<>("aniversari"));

        tv_1.setItems(llistaObservableClients);
    }

}
