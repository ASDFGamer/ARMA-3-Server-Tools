package org.asdfgamer.arma_tools.config;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import org.asdfgamer.hilfreich.FileUtil;


/**
 * Dies sind alle Einstellungen die existieren.
 *
 * @author ASDFGamer
 */
public enum Einstellungen {

    test("Testwert");
    /**
     * Der Logger den wir benutzen.
     */
    private final static Logger LOG = Logger.getLogger(Einstellungen.class.getName());

    /**
     * Die Property in der die Einstellungen gespeichert werden.
     */
    private EinstellungenProperty property;

    /**
     * Dies legt fest ob die Einstellung in die Einstellungsdatei geschrieben werden soll.
     */
    private boolean internerWert = false;

    /**
    * Dies legt fest, ob die Einstellung in Form eines boolschen Werts geaendert wurde.
    */
    private static boolean einstellungenGeaendert = false;

    /**
     * Der Standardkonstruktor falls kein Standardwert eingegeben wurde.
     */
    private Einstellungen() {
        this.property = new EinstellungenProperty();
        this.property.addListener(einstellungenAendern);
    }

    /**
     * Der Konstruktor für Einstellungen aus Strings.
     *
     * @param standardwert Der Standardwert der Einstellung.
     */
    private Einstellungen(String standardwert) {
        this.property = new EinstellungenProperty(standardwert);
        this.property.addListener(einstellungenAendern);
    }

    /**
     * Der Konstruktor für Einstellungen aus Integern.
     *
     * @param standardwert Der Standardwert der Einstellung.
     */
    private Einstellungen(int standardwert) {
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
        this.property.addListener(einstellungenAendern);
    }

    /**
     * Der Konstruktor für Einstellungen aus Integern.
     *
     * @param standardwert Der Standardwert der Einstellung.
     * @param minimalwert  Der niedrigste erlaubte Wert.
     * @param maximalwert  Der höchste erlaubte Wert.
     */
    private Einstellungen(int standardwert, int minimalwert, int maximalwert) {
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
        this.property.setMinWert(minimalwert);
        this.property.setMaxWert(maximalwert);
        this.property.addListener(einstellungenAendern);
    }

    /**
     * Der Konstruktor für Einstellungen aus boolschen Wahrheitswerten.
     *
     * @param standardwert Der Standardwert der Einstellung.
     */
    private Einstellungen(boolean standardwert) {
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
        this.property.setBoolean(standardwert);
        this.property.addListener(einstellungenAendern);
    }

    /**
     * Der Konstruktor für Einstellungen aus double Zahlen.
     *
     * @param standardwert Der Standardwert der Einstellung.
     */
    private Einstellungen(double standardwert) {
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
        this.property.setDouble(standardwert);
        this.property.addListener(einstellungenAendern);
    }

    /**
     * Der Konstruktor für Einstellungen aus double Zahlen.
     *
     * @param standardwert Der Standardwert der Einstellung.
     * @param minimalwert  Der niedrigste erlaubte Wert.
     * @param maximalwert  Der höchste erlaubte Wert.
     */
    private Einstellungen(double standardwert, double minimalwert, double maximalwert) {
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
        this.property.setMinWert(minimalwert);
        this.property.setMaxWert(maximalwert);
        this.property.addListener(einstellungenAendern);
    }

    /**
     * Der Konstruktor für Einstellungen aus Strings.
     *
     * @param standardwert Der Standardwert der Einstellung.
     * @param intern       Dies gibt an ob die Einstellung nur intern sein soll oder auch in der einstellungsdate gespeichert werden soll.
     */
    private Einstellungen(String standardwert, boolean intern) {
        this.internerWert = intern;
        this.property = new EinstellungenProperty(standardwert);
    }

    /**
     * Der Konstruktor für Einstellungen aus Integern.
     *
     * @param standardwert Der Standardwert der Einstellung.
     * @param intern       Dies gibt an ob die Einstellung nur intern sein soll oder auch in der einstellungsdate gespeichert werden soll.
     */
    private Einstellungen(int standardwert, boolean intern) {
        this.internerWert = intern;
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
    }

    /**
     * Der Konstruktor für Einstellungen aus boolschen Wahrheitswerten.
     *
     * @param standardwert Der Standardwert der Einstellung.
     * @param intern       Dies gibt an ob die Einstellung nur intern sein soll oder auch in der Einstellungsdatei gespeichert werden soll.
     */
    private Einstellungen(boolean standardwert, boolean intern) {
        this.internerWert = intern;
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
        this.property.setBoolean(standardwert);
    }

    /**
     * Der Konstruktor für Einstellungen aus double Zahlen.
     *
     * @param standardwert Der Standardwert der Einstellung.
     * @param intern       Dies gibt an ob die Einstellung nur intern sein soll oder auch in der Einstellungsdatei gespeichert werden soll.
     */
    private Einstellungen(double standardwert, boolean intern) {
        this.internerWert = intern;
        this.property = new EinstellungenProperty(String.valueOf(standardwert));
        this.property.setDouble(standardwert);
    }

    /**
     * Dies ist der Listender der die Variable 'einstellungenGeaendert' auf true setzt, falls sich Einstellungen geändert haben.
     * Dies wird dafür gebraucht, dass nur neu abgespeichert wird falls sich etwas geändert hat.
     */
    private final ChangeListener<Object> einstellungenAendern = new ChangeListener<Object>() {
        @Override
        public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
            if (oldValue != null && newValue != null && !oldValue.equals(newValue)) {
                Einstellungen.einstellungenGeaendert = true;
            }
        }
    };

    /**
     * Dies gibt den String dieser Einstellung zurück.
     * Dies ist nur eine Kurzfassung von getProperty.get().
     *
     * @return Der zugehörige Wert.
     */
    public String getWert() {
        return this.property.get();
    }
    
    /**
     * Dies gibt den Integerwert dieser Einstellung zurück.
     * Dies ist nur eine Kurzfassung von getProperty.getInteger().
     *
     * @return Der zugehörige Wert.
     */
    public Integer getInteger() {
        return this.property.getInteger();
    }
    
    /**
     * Dies gibt den Doublewert dieser Einstellung zurück.
     * Dies ist nur eine Kurzfassung von getProperty.getDouble().
     *
     * @return Der zugehörige Wert.
     */
    public Double getDouble() {
        return this.property.getDouble();
    }

    /**
     * Dies ändert die Einstellung.
     * Dies ist eine Kurzfassung von getProperty.set(neuerWert) mit einem catch für die Exception.
     *
     * @param neuerWert Der neue Wert.
     * @return true, falls das Speichern geklappt hat, ansonsten false.
     */
    public boolean setWert(String neuerWert) {
        try {
            this.property.set(neuerWert);
        } catch (IllegalArgumentException e) {
            LOG.log(Level.WARNING, "Der Wert:{0} konnte nicht gespeichert werden.", neuerWert);
            return false;
        }
        return true;
    }

    /**
     * Dies ändert die Einstellung.
     * Dies ist eine Kurzfassung von getProperty.setInteger(neuerWert).
     *
     * @param neuerWert Der neue Wert.
     * @return true, falls das ändern geklappt hat, ansonsten false.
     */
    public boolean setWert(int neuerWert) {
        return this.property.setInteger(neuerWert);
    }

    /**
     * Dies ändert die Einstellung.
     * Dies ist eine Kurzfassung von getProperty.setDouble(neuerWert).
     *
     * @param neuerWert Der neue Wert.
     * @return true, falls das ändern geklappt hat, ansonsten false.
     */
    public boolean setWert(double neuerWert) {
        return this.property.setDouble(neuerWert);
    }

    /**
     * Dies ändert die Einstellung.
     * Dies ist eine Kurzfassung von getProperty.setBoolean(neuerWert).
     *
     * @param neuerWert Der neue Wert.
     * @return true, falls das ändern geklappt hat, ansonsten false.
     */
    public boolean setWert(boolean neuerWert) {
        return this.property.setBoolean(neuerWert);
    }

    /**
     * Dies gibt die Property der Einstellung zurück.
     *
     * @return Die zugehörige Property.
     */
    public EinstellungenProperty getProperty() {
        return this.property;
    }

    /**
     * Dies lädt die Einstellungen von dem Standardspeicherort.
     *
     * @return true, falls das Laden geklappt hat, ansonsten false.
     */
    public static boolean laden() {
        return Einstellungen.laden(FileUtil.getConfigFile(Const.PROGRAMM_NAME, "config.txt"));
    }

    /**
     * Dies Lädt die Einstellungen von dem angegebenen Pfad
     *
     * @param pfad Die Datei in der die Einstellungenn liegen.
     * @return true, falls das Laden geklappt hat, ansonsten false.
     */
    public static boolean laden(String pfad) {
        boolean result = true;
        Properties properties = new Properties();
        InputStream configFile = null;

        for (Einstellungen einstellung : Einstellungen.values()) {
            if (!einstellung.internerWert) {
                einstellung.getProperty().removeListener(einstellung.einstellungenAendern);
            }
        }

        try {
            configFile = new FileInputStream(pfad);
            properties.load(configFile);
            for (Einstellungen einstellung : Einstellungen.values()) {
                einstellung.getProperty().set(properties.getProperty(einstellung.name(), einstellung.getProperty().getStandardwert()));
            }
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Die Configfile konnte nicht geladen werden.", e);
            result = false;
        } finally {
            if (configFile != null) {
                try {
                    configFile.close();
                } catch (IOException e) {
                    LOG.log(Level.SEVERE, "Die Configfile konnte nicht geschlossen werden.", e);
                }
            }
        }

        for (Einstellungen einstellung : Einstellungen.values()) {
            if (!einstellung.internerWert) {
                einstellung.getProperty().addListener(einstellung.einstellungenAendern);
            }
        }

        return result;
    }

    /**
     * Dies speichert alle Einstellungen in die Standardeinstellungsdatei.
     *
     * @return true, falls das Speichern geklappt hat, ansonsten false.
     */
    public static boolean speichern() {
        return Einstellungen.speichern(FileUtil.getConfigFile(Const.PROGRAMM_NAME, "config.txt"));
    }

    /**
     * Dies speichert alle Einstellungen in die Standardeinstellungsdatei.
     *
     * @param pfad Der Pfad zu der Datei in die Einstlllungen gespeichert werden sollen.
     * @return true, falls das Speichern geklappt hat, ansonsten false.
     */
    public static boolean speichern(String pfad) {
        boolean result = true;
        if (!Einstellungen.einstellungenGeaendert) {
            LOG.info("Es wurde keine Einstellung geändert, deshalb muss nichts gespeichert werden.");
        }
        Properties properties = new Properties();
        OutputStream configFile = null;

        try {
            configFile = new FileOutputStream(pfad);

            for (Einstellungen einstellung : Einstellungen.values()) {
                if (!einstellung.internerWert) {
                    properties.setProperty(einstellung.name(), einstellung.getProperty().get());
                }
            }

            properties.store(configFile, "Dies sind die Einstellungen des Spiels '" + Const.PROGRAMM_NAME + "'.");
        } catch (IOException e) {
            LOG.log(Level.WARNING, "Es gab Probleme beim Öffnen der Datei zum Speichern der Einstellungen", e);
            result = false;
        } finally {
            if (configFile != null) {
                try {
                    configFile.close();
                } catch (IOException e) {
                    LOG.log(Level.SEVERE, "Die Configfile konnte nicht geschlossen werden.", e);
                }
            }
        }
        return result;
    }
}