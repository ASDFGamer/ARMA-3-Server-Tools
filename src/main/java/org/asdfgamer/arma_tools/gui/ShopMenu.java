/*
 * Copyright (C) 2017 ASDFGamer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.asdfgamer.arma_tools.gui;

import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author ASFDGamer
 */
public class ShopMenu
{
    private final static Logger LOG = Logger.getLogger(ShopMenu.class.getName());
    
    private Stage stage;
    
    /**
     * Erstellt eine ConfigStage in der der gegebenen Stage.
     * @param stage Die Stage die zu einer ConfigStage werden soll.
     * @param uebergeordnet Die Stage die dieser übergeordnet ist und somit zum Owner wird und nicht zu benutzten ist solange dieses Fenster offen ist.
     * @throws IOException falls auf die fxml nicht zugegriffen werden kann.
     */
    public ShopMenu (Stage stage, Stage uebergeordnet) throws IOException
    {
        Parent root;
        stage.hide();
        try
        {
            root = FXMLLoader.load(getClass().getResource("/fxml/ShopMenu.fxml"));
        } catch (IOException ex)
        {
            LOG.log(Level.SEVERE, "Es konnte die ShopMenu.fxml nicht geladen werden.", ex);
            throw new IOException("Es konnte die fxml Datei für das ShopMenu nicht gefunden werden.");
        }
        Scene scene = new Scene(root, 600, 400); //Pixel anpassen
        stage.setTitle("ArmA 3 Shop Editor");
        stage.initOwner(uebergeordnet);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setScene(scene);
        stage.show();
        this.stage = stage;
    }
}
