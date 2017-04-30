package org.asdfgamer.arma_tools.datatools;

import java.net.URL;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;

/**
 * Diese Klasse ist für die Verwaltung von MySQL zuständig.
 *
 * @author ASDFGamer
 */
public class LoadMySQL
{

    /**
     * Dies ist die Connection mit der gearbeitet wird.
     */
    private Connection connection = null;

    /**
     * Der normale LOG.
     */
    private final static Logger LOG = Logger.getLogger(LoadMySQL.class.getName());

    /**
     * Dies erstellt ein neues Objekt um mit einer MySQL Daatenbank umzugehen.
     *
     * @param addresse Die Adresse zu der Datenbank in dem Format:
     *                 jdbc:subprotocol:subname, z.B.
     *                 "jdbc:mysql://localhost/test?"+"user=minty&password=greatsqldb"
     * @throws IllegalArgumentException Dies wird geworfen, wenn die Connection nicht zustandekommt.
     */
    public LoadMySQL(String addresse) throws IllegalArgumentException
    {
        LOG.info("Es wird versucht sich mit der MySQL Datenbank " + addresse + " zu verbinden.");
        try
        {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (ClassNotFoundException ex)
        {
            LOG.log(Level.SEVERE, "Die angegebene Klasse konnte nicht gefunden werden.", ex);
            throw new IllegalArgumentException("Es gab Probleme mit der Driverklasse.");
        } catch (InstantiationException ex)
        {
            LOG.log(Level.SEVERE, "Es konnte keine Instanz der Driverklasse erstellt werden.", ex);
            throw new IllegalArgumentException("Es gab Probleme mit der Driverklasse.");
        } catch (IllegalAccessException ex)
        {
            LOG.log(Level.SEVERE, "Es besteht kein Zugriff auf die Driverklasse.", ex);
            throw new IllegalArgumentException("Es gab Probleme mit der Driverklasse.");
        }
        LOG.fine("Die Dirverklasse wurde initialisiert.");
        try
        {
            connection = DriverManager.getConnection(addresse);
        } catch (SQLException ex)
        {
            LOG.log(Level.SEVERE, "Es gab Fehler beim verbinden mit der Datenbank.");
            LOG.log(Level.SEVERE, "SQLException: ", ex.getMessage());
            LOG.log(Level.SEVERE, "SQLState: ", ex.getMessage());
            LOG.log(Level.SEVERE, "VendorError: ", ex.getErrorCode());
            throw new IllegalArgumentException("Es konnte keine verbindung zu der angegebenen Adresse erstellt werden.");
        }
    }
}
