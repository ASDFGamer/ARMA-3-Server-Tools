package org.asdfgamer.arma_tools.gui;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AuswahlfensterController implements Initializable
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

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        // TODO
    }
}
