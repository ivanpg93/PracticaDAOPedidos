package presentacio;

import aplicacio.App;
import aplicacio.ClientLogic;
import aplicacio.LogicLayerException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import model.Client;
import utils.Alerts;

/**
 *
 * @author Ivan
 */
public class EditClientController extends PresentationLayer implements Initializable {

    String email;
    Client c;
    Alerts alert = new Alerts();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    public TextField txtEmail, txtDni, txtNom, txtTelefon, txtLimitCredit, txtAniversari;

    @FXML
    private void switchToClients() throws IOException {
        App.setRoot("clients");
    }

    @FXML
    // Setegem els txt amb els valors del Client
    private void recuperarDatos() {
        txtEmail.setText(c.getEmail());
        txtDni.setText(c.getDni());
        txtNom.setText(c.getNom());
        txtTelefon.setText(c.getTelefon());
        txtLimitCredit.setText(String.valueOf(c.getLimitCredit()));
        txtAniversari.setText(String.valueOf(c.getAniversari()));

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
    // Editar els valors del Client en la BBDD amb les dades del formulari
    private void editarClient(ActionEvent event) throws IOException, ParseException, LogicLayerException {
        // Revisem si tot el formulari està emplenat
        if (txtEmail.getText() == "" || txtDni.getText() == "" || txtNom.getText() == "" || txtTelefon.getText() == "" || txtLimitCredit.getText() == "" || txtAniversari.getText() == "") {
            alert.mostrarAlertOmplirFormulari(event);
        } else {
            // Modifiquem els valors del objecte client segons les dades del formulari
            c.setEmail(txtEmail.getText());
            c.setDni(txtDni.getText());
            c.setNom(txtNom.getText());
            c.setTelefon(txtTelefon.getText());
            c.setLimitCredit(Double.valueOf(txtLimitCredit.getText()));
            
            // Formatm la data per a poder afegir-la en la bbdd
            java.util.Date date = sdf.parse(txtAniversari.getText());
            java.sql.Date sqlDate = new Date(date.getTime());
            c.setAniversari(sqlDate);
            
            // Calculem els anys del Client per saber si es major d'edat
            Calendar cal = Calendar.getInstance();
            int anyoActual = cal.get(Calendar.YEAR);
            int anyoClient = Integer.valueOf(c.getAniversari().toString().substring(0, 4));
            if (anyoActual - anyoClient >= 18) {
                this.clientLogic.afegirClient(c);
                switchToClients();
            } else {
                alert.mostrarAlertMinCustomerAge(event);
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.clientLogic = new ClientLogic();
            // Agafem els valors del Client a modificar
            email = this.clientLogic.getEmailClient();
            c = this.clientLogic.getClient(email);
            recuperarDatos();
        } catch (LogicLayerException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
