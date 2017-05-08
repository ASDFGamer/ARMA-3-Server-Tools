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
package org.asdfgamer.arma_tools.controll.shop;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asdfgamer.arma_tools.model.shop.*;

/**
 * Dies lädt die Config_* Datei.
 *
 * @author ASDFGamer
 */
public class Shoploader
{

    /**
     * Dies ist der Zeichensatz der in den Dateien verwendet wird. Mehrzeilige
     * Kommentare müssen immer in einer neuen Zeile enden:<br>
     * <p>
     * {@literal /* }<br> {@literal * }ssf<br> {@literal * }<br>
     * {@literal *\/}<br>
     * <p>
     */
    static private final String CHARSETNAME = "UTF-8";

    private Shop currentShop;
    
    private ShopItems currentShopItems;
    
    private final String name;

    private ShopKategorie kategorie = null;

    private LoadStates state = LoadStates.nichts;
    
    private int zeilennummer = 0;

    private final static Logger LOG = Logger.getLogger(Shoploader.class.getName());

    public Shoploader(String filepath)
    {
        this.name = filepath;
        LOG.info("Es wird mit dem Laden der Shopdatei: " + this.name + " begonnen.");
        loadFile();
    }

    public ShopKategorie getKategorie()
    {
        return this.kategorie;
    }
    
    /**
     * Diese Methode gibt den Klassennamen aus einer Klassendeklaration zurück ( class ASDF \{ wird zu ASDF).
     * @param textzeile Die Zeile mit dem Klassennamen.
     * @return Der Klassenname.
     */
    private String getClassname(String textzeile)
    {
        String classname = textzeile.replaceAll("\\s+","");
        return classname.substring(5,classname.length()-1);
    }

    private String getInhalt(String textzeile)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean interpretiereZeile(String zeile)
    {
        boolean ergebnis = true;
        String textzeile = zeile.trim();
        if (textzeile.equals("") || textzeile.startsWith("//"))
        {
            return true;
        } else if (textzeile.startsWith("/*"))
        {
            this.state = LoadStates.Kommentar;
            return true;
        } else if (textzeile.startsWith("*"))
        {
            if (textzeile.startsWith("*/"))
            {
                this.state = LoadStates.nichts;
                return true;
            } else
            {
                return true;
            }

        } else if (textzeile.startsWith("class"))
        {
            if (null == state)
            {
                LOG.warning("In Zeile " + this.zeilennummer + " ist ein Interner Fehler aufgetreten, da der state gleich null ist (in interpretiereZeile()).");
                return false;
            }
            else switch (state)
            {
                case nichts:
                    this.kategorie = new ShopKategorie(getClassname(textzeile));
                    this.state = LoadStates.Kategorie;
                    return true;
                case Kategorie:
                    this.currentShop =new Shop(getClassname(textzeile));
                    this.state = LoadStates.Shop;
                    return true;
                default:
                    LOG.warning("In der Zeile " + this.zeilennummer + " darf keine Klassendeklaration stehen("+ textzeile + ").");
                    return false;
            }
        }
        else if (state == LoadStates.Shop)
        {
            return loadShop(textzeile);
        }
        else if (state == LoadStates.Items)
        {
            return loadItems(textzeile);
        }
        return ergebnis;
    }

    private boolean loadFile()
    {
        Charset charset = Charset.forName(CHARSETNAME);
        Path path = Paths.get(this.name);
        try (BufferedReader reader = Files.newBufferedReader(path, charset))
        {
            String zeile;
            while ((zeile = reader.readLine()) != null)
            {
                this.zeilennummer ++;
                if (!interpretiereZeile(zeile))
                {
                    LOG.severe("Das Laden der Datei wird wegen einem kritischen Fehler abgebrochen!");
                    return false;
                }
            }
        } catch (IOException e)
        {
            LOG.log(Level.SEVERE, e, () -> "IOException: %s%n beim öffnen der Datei ");
        }
        return true;

    }

    private boolean loadItems(String textzeile)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private boolean loadShop(String textzeile)
    {
        if (textzeile.startsWith("title"))
        {
            this.currentShop.addTitle(getInhalt(textzeile));
        }
        else if (textzeile.startsWith("license"))
        {
            this.currentShop.addLicense(getInhalt(textzeile));
        }
        else if (textzeile.startsWith("side"))
        {
            this.currentShop.addSide(getInhalt(textzeile));
        }
        else if ((textzeile.replaceAll("\\s+","")).contains("[]={"))//uniforms, headgear, goggles,...
        {
            this.currentShopItems = new ShopItems();
            this.state= LoadStates.Items;
            return true;
        }
        else if (textzeile.startsWith("};"))
        {
            this.kategorie.addShop(currentShop);
            this.state=LoadStates.Kategorie;
            return true;
        }
        LOG.warning("Die Zeile " + this.zeilennummer + " kann nicht als Inhalt eines Shops interpretiert werden (" + textzeile + ").");
        return false;
    }
}

/**
 * Dies sind die verschiedenen Status in der der Loader sein kann.
 *
 * @author ASDFGamer
 */
enum LoadStates
{
    nichts,
    Kommentar,
    Kategorie,
    Shop,
    Items;
}
