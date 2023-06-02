/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacio;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 * Generalitza un controlador
 * @author Ivan
 */
public abstract class PresentationLayer {
    
    aplicacio.ClientLogic clientLogic;
    aplicacio.ProducteLogic producteLogic;
    aplicacio.ComandaLogic comandaLogic;
    aplicacio.LiniaComandaLogic liniaComandaLogic;
    
    /***
     * Finestra associada a cada controller
     */
    private Stage stage;

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    /***
     * Mostra un missatge d'error
     * @param txt 
     */
    public void mostraError(String txt)
    {
          Alert a = new Alert(Alert.AlertType.ERROR, "", ButtonType.OK);
          a.setTitle("ERROR: ");
          a.setContentText(txt);
          
          //refresquem vista
          //App.getStage().setScene(scene);
          
          //mostrem error
          a.show();
    }
    
    /***
     * Permet a les subclasses reimplementar aquest mètode
     * A diferència d'una interfície, aquesta reimplementació no és
     * obligatòria
     */
    public abstract void close();
    
}
