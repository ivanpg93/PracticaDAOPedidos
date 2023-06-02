package presentacio;

import aplicacio.App;
import java.io.IOException;
import javafx.fxml.FXML;

public class ComandesController {

    @FXML
    private void switchToClients() throws IOException {
        App.setRoot("clients");
    }
    
    @FXML
    private void switchToProductes() throws IOException {
        App.setRoot("productes");
    }
    
    @FXML
    private void switchToAfegirComanda() throws IOException {
        App.setRoot("novaComanda");
    }


}