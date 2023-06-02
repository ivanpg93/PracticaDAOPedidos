package presentacio;

import aplicacio.App;
import aplicacio.ComandaLogic;
import aplicacio.LiniaComandaLogic;
import aplicacio.LogicLayerException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.Client;
import model.Comanda;

public class AddComandaController extends PresentationLayer implements Initializable{

    Comanda c = new Comanda();
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @FXML
    private void switchToLiniaComanda() throws IOException {
        App.setRoot("liniaComanda");
    }

    @FXML
    public TextField txtNumero, txtDataComan, txtDataDesitjada, txtDataEnvio, txtClient;

    @FXML
    Button btnGuardar, btnCancelar, afegirLinea, btnEliminarLinea;

    @FXML
    public void cancelarComanda() {
        try {
            if (this.comandaLogic != null) {
                System.out.println("Tancant comandaController...");
                this.comandaLogic.close();
            }

        } catch (LogicLayerException ex) {
            System.out.println("No s'ha pogut finalitzar correctament el controller: " + ex);
        }
    }

    @FXML
    private void crearComanda(ActionEvent event) throws IOException, LogicLayerException {
        Comanda comanda = comandaLogic.getComanda(Integer.parseInt(txtNumero.getText()));
        // Modifiquem els valors del objecte client segons les dades del formulari
        c.setNombreComanda(liniaComandaLogic.getNextId(comanda));
        c.setEmail(clientLogic.getClient(txtClient.getText()) );

        java.util.Date date = sdf.parse(txtNumero.getText());
        java.sql.Date sqlDate = new Date(date.getTime());
        c.setDataComanda(sqlDate);
        
        java.util.Date data = sdf.parse(txtDataDesitjada.getText());
        java.sql.Date sqlData = new Date(date.getTime());
        c.setDataDesitjada(sqlData);
        
        java.util.Date dati = sdf.parse(txtDataEnvio.getText());
        java.sql.Date sqlDati = new Date(date.getTime());
        c.setDataDesitjada(sqlDati);
        
        this.comandaLogic.save(c);

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.comandaLogic = new ComandaLogic();
            this.liniaComandaLogic = new LiniaComandaLogic();

        } catch (LogicLayerException ex) {
            Logger.getLogger(ClientsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void close() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
