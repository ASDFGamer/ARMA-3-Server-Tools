package org.asdfgamer.arma_tools.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Auswahlfenster extends Application
{

    private final static Logger LOG = Logger.getLogger(Auswahlfenster.class.getName());

    @Override
    public void start(Stage stage) throws Exception
    {
        if (!loadScene(stage))
        {
            throw new IOException("Es gab ein Problem beim Laden der Scene.");
        }

    }

    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
    
    /**
     * Diese Methode lädt die Scene.
     * @param stage Die Stage in die die Scene soll.
     * @return true, falls das Laden geklappt hat, ansonsten false.
     */
    public boolean loadScene(Stage stage)
    {
        stage.hide();
        Parent root;
        try
        {
            root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        } catch (IOException ex)
        {
            LOG.log(Level.SEVERE, "Es konnte die Auswahlfenster.fxml nicht geladen werden.", ex);
            return false;
        }

        Scene scene = new Scene(root);
        scene.getStylesheets().add("/styles/Styles.css");

        stage.setTitle("JavaFX and Maven");
        stage.setScene(scene);
        stage.show();
        return true;
    }

}
