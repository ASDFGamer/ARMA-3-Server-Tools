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
package org.asdfgamer.arma_tools.controll.playerTools;

import org.asdfgamer.arma_tools.controll.mySQL.MySQLTools;
import java.util.logging.Logger;
import org.asdfgamer.arma_tools.controll.config.Einstellungen;

/**
 * Diese Klasse liefert Daten über alle Spieler.
 * @author ASDFGamer
 */
public class PlayerData
{
    private MySQLTools sqltools;
    
    private static final Logger LOG = Logger.getLogger(PlayerEdit.class.getName());
    
    /**
     * Dies erstellt ein neues Objekt um mit den Playerdaten umzugehen.
     * Die Adresse zu der Datenbank wird aus den Einstellungen geladen.
     * @throws IllegalArgumentException Dies wird geworfen, wenn die Connection nicht zustandekommt.
     */
    public PlayerData()
    {
        String adresse = "jdbc:mysql://"+Einstellungen.serverip.getWert()+"/"+Einstellungen.mySQLStruktur.getWert()
                + "?user="+ Einstellungen.mySQLBenutzername.getWert() + "&password" + Einstellungen.mySQLPasswort.getWert();
        sqltools = new MySQLTools(adresse);
        if(!sqltools.tableExists("players"))
        {
            LOG.warning("Die Tabelle 'players' existiert nicht in der Struktur '" + Einstellungen.mySQLStruktur.getWert() + "'.");
        }
    }
    
    public String[] getPlayerNames()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int getPlayerAnzahl()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public int getPlayerUIDs()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
