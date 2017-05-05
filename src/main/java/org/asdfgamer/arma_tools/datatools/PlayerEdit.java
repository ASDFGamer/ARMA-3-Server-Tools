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
package org.asdfgamer.arma_tools.datatools;

import java.util.Date;
import java.util.logging.Logger;
import org.asdfgamer.arma_tools.config.Einstellungen;

/**
 * Diese Klasse liefert Daten über einen bestimmten Spieler.
 * @author ASDFGamer
 */
public class PlayerEdit
{
    private MySQLTools sqltools;
    
    private final int ID;
    
    private String[] playerData = new String[26];
    
    private static final Logger LOG = Logger.getLogger(PlayerEdit.class.getName());
    
    /**
     * Dies erstellt ein neues Objekt um mit den Playerdaten umzugehen.
     * Die Adresse zu der Datenbank wird aus den Einstellungen geladen.
     * @param id Die uid oder plaerid des Spielers.
     * @throws IllegalArgumentException Dies wird geworfen, wenn die Connection nicht zustandekommt.
     */
    public PlayerEdit(int id)
    {
        this.ID = id;
        init();    
    }
    
    /**
     * Dies erstellt ein neues Objekt um mit den Playerdaten umzugehen.
     * Die Adresse zu der Datenbank wird aus den Einstellungen geladen.
     * @param name Der Name oder Alias des Spielers
     * @throws IllegalArgumentException Dies wird geworfen, wenn die Connection nicht zustandekommt.
     */
    public PlayerEdit(String name)
    {
        this.ID = nameToId(name);
        init();
    }
    
    
    private void init()
    {
        String adresse = "jdbc:mysql://"+Einstellungen.serverip.getWert()+"/"+Einstellungen.mySQLStruktur.getWert()
                + "?user="+ Einstellungen.mySQLBenutzername.getWert() + "&password" + Einstellungen.mySQLPasswort.getWert();
        sqltools = new MySQLTools(adresse);
        if(!sqltools.tableExists("players"))
        {
            LOG.warning("Die Tabelle 'players' existiert nicht in der Struktur '" + Einstellungen.mySQLStruktur.getWert() + "'.");
        }
    }
    
    /**
     * Dies gibt den Playernamen zurück.
     * @return Der Name des Spielers.
     */
    public String getPlayerName()
    {
        
    }
    
    /**
     * Dies gibt die uid des Spielers zurück.
     * @return Die uid des Spielers.
     */
    public String getPlayeruid()
    {
        
    }
    
    /**
     * Dies gibt alle Infos zu einem Spieler aus.
     * @return 
     */
    public String[] getPlayerData()
    {
        
    }
    
    public int getCash()
    {
        
    }
    
    public boolean setCash(int cash)
    {
        
    }
    
    public int getDeposit()
    {
        
    }
    
    public boolean setDeposit(int bankacc)
    {
        
    }
    
    public int getCopLevel()
    {
        
    }
    
    public boolean setCopLevel(int copLevel)
    {
        
    }
    
    public int getMedicLevel()
    {
        
    }
    
    public boolean setMedicLevel(int medicLevel)
    {
        
    }
    
    public String[] getCivLicenses()
    {
        
    }
    
    public boolean setCivLicenses(String[] civLicenses)
    {
        
    }
    
    public boolean addCivLicense(String civLicense)
    {
        
    }
    
    public boolean removeCivLicense(String civLicense)
    {
        
    }
    
    public String[] getCopLicenses()
    {
        
    }
    
    public boolean setCopLicenses(String[] copLicenses)
    {
        
    }
    
    public boolean addCopLicense(String copLicense)
    {
        
    }
    
    public boolean removeCopLicense(String copLicense)
    {
        
    }
    
    public String[] getMedicLicenses()
    {
        
    }
    
    public boolean setMedicLicenses(String[] medicLicenses)
    {
        
    }
    
    public boolean addMedicLicense(String medicLicense)
    {
        
    }
    
    public boolean removeMedicLicense(String medicLicense)
    {
        
    }
    
    public Date getLastPlaytime()
    {
        
    }
    
    public Date getFirstPlaytime()
    {
        
    }
    
    public Date getCopPlaytime()//Cop == west
    {
        
    }
    
    public Date getMedicPlaytime()//Medic == independent
    {
        
    }
    
    public Date getCivPlaytime()
    {
        
    }

    private int nameToId(String name)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
   
}
