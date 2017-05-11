package org.asdfgamer.arma_tools.controll.guicontroller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.asdfgamer.arma_tools.gui.ShopMenu;

public class AuswahlfensterController extends Controller 
{
    private final static Logger LOG = Logger.getLogger(AuswahlfensterController.class.getName());
    
    @FXML
    private Label lblTitle;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnPlayerManager;
    
    @FXML
    private Button btnFahrzeugManager;
    
    @FXML
    private Button btnKonfigs;
    
    @FXML
    private Button btnLogs;
    
    @FXML
    private Button btnShops;
    
    @FXML
    private void handleBtnCloseAction(ActionEvent event)
    {
        LOG.fine("Der Button btnClose wurde gedrückt.");
    }
    
    @FXML
    private void handleBtnPlayerManagerAction(ActionEvent event)
    {
        LOG.fine("Der Button btnPlayerManager wurde gedrückt.");
    }
    
    @FXML
    private void handleBtnFahrzeugManagerAction(ActionEvent event)
    {
        LOG.fine("Der Button btnFahrzeugManager wurde gedrückt.");
    }
    
    @FXML
    private void handleBtnKonfigsAction(ActionEvent event)
    {
        LOG.fine("Der Button btnKonfigs wurde gedrückt.");
    }
    
    @FXML
    private void handleBtnLogsAction(ActionEvent event)
    {
        LOG.fine("Der Button btnLogs wurde gedrückt.");
    }
    
    @FXML
    private void handleBtnShopsAction(ActionEvent event)
    {
        Stage stage = new Stage();
        try {
            Stage thisStage = (Stage)btnShops.getScene().getWindow();
            ShopMenu confstage = new ShopMenu(stage,thisStage);
        } catch (IOException ex) {
            LOG.warning("Es gab ein Problem beim öffnen der Configseite");
            ex.printStackTrace();
            stage.close();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}
