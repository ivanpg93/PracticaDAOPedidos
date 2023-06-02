/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author Ivan
 */
public class Alerts {
    
    @FXML
    public void mostrarAlertItemNoSelected(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("S'ha de seleccionar un ítem de la taula");
        alert.showAndWait();
    }
    
    @FXML
    public void mostrarAlertOmplirFormulari(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("Ompli tots els camps del formulari");
        alert.showAndWait();
    }
    
    @FXML
    public void mostrarAlertMinCustomerAge(ActionEvent event) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText("El client ha de ser major d'edat");
        alert.showAndWait();
    }

}
