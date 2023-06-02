package aplicacio;

import dades.DataLayer;
import dades.MySQLConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author Ivan JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Manager manager;

    @Override
    public void start(Stage primaryStage) throws IOException {

        try {

            String fxml = "vista/principal";

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getClassLoader().getResource(fxml + ".fxml"));

            Parent p = fxmlLoader.load();

            scene = new Scene(p);

            manager = Manager.getInstance();

            primaryStage.setScene(scene);

            primaryStage.show();

        } catch (IOException ex) {

            System.out.println("Ni idea del que pot haver passat... ");
            System.out.println(ex.toString());
        }
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("../vista/" + fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void starter(String[] args) throws SQLException {
        MySQLConnector.ConnectarBD("m03uf6_22_23", "root", "1234");
        Connection con = DataLayer.con;
        
        launch();
    }

    public static Manager getManager() {
        return manager;
    }

}
