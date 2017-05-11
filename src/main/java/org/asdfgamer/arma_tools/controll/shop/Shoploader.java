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

import org.asdfgamer.arma_tools.model.config.Shop;
import org.asdfgamer.arma_tools.model.config.ShopItems;
import org.asdfgamer.arma_tools.model.config.ShopKategorie;
import org.asdfgamer.arma_tools.model.config.ShopLevel;
import org.asdfgamer.arma_tools.model.config.VarType;
import org.asdfgamer.arma_tools.model.config.ShopItem;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asdfgamer.hilfreich.Convertable;

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
    private final static Logger LOG = Logger.getLogger(Shoploader.class.getName());

    private Shop currentShop;

    private ShopItems currentShopItems;

    private ShopKategorie kategorie = null;
    private final String name;

    private LoadStates state = LoadStates.nichts;

    private int zeilennummer = 0;

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

    private boolean createItem(String textzeile)
    {
        String text = textzeile.substring(textzeile.indexOf("\"") + 1);
        String itemname = text.substring(1, text.indexOf("\"") - 1);
        text = text.substring(text.indexOf("\"") + 1);
        String itemtext = text.substring(1, text.indexOf("\"") - 1);
        text = text.substring(text.indexOf(",") + 1);
        String priceS = text.substring(0, text.indexOf(","));
        int price;
        if (Convertable.toInt(priceS))
        {
            price = Integer.parseInt(priceS);
        } else
        {
            LOG.warning("Der Preis des Items in der Zeile " + this.zeilennummer + " kann nicht in eine Zahl umgewandelt werden.");
            return false;
        }
        text = text.substring(text.indexOf(",") + 1);
        if (text.startsWith("{"))
        {
            ShopLevel level = createLevel(text);
            this.currentShopItems.addShopItem(new ShopItem(itemname, itemtext, price, level));
        } else
        {
            String sellpriceS = text.substring(0, text.indexOf(","));
            int sellprice = -1;
            if (Convertable.toInt(sellpriceS))
            {
                sellprice = Integer.parseInt(sellpriceS);
            } else
            {
                LOG.warning("Der Verkaufspreis des Items in der Zeile " + this.zeilennummer + " kann nicht in eine Zahl umgewandelt werden.");
                return false;
            }
            this.currentShopItems.addShopItem(new ShopItem(text, itemname, price, sellprice));
        }

        return true;

    }

    private ShopLevel createLevel(String levelText)
    {
        String text = levelText.substring(levelText.indexOf("{") + 1);
        String variable = text.substring(1, text.indexOf(","));
        text = text.substring(text.indexOf(",") + 1);
        String varTypeS = text.substring(1, text.indexOf(",") - 1);
        VarType varType;
        switch (varTypeS)
        {
            case "SCALAR":
                varType = VarType.SCALAR;
                break;
            case "BOOL":
                varType = VarType.BOOL;
                break;
            case "EQUAL":
                varType = VarType.EQUAL;
                break;
            default:
                varType = VarType.SCALAR;
        }
        text = text.substring(text.indexOf(",") + 1);
        String compareTo;
        if (text.contains(","))
        {
            compareTo = text.substring(0, text.indexOf(",")); //TODO vllt schauen wegen Typproblemen
            text = text.substring(text.indexOf(",") + 1);
            String errmessage = text.substring(text.indexOf("}"));
            return new ShopLevel(variable, varType, compareTo, errmessage);
        } else
        {
            compareTo = text.substring(0, text.indexOf("}")); //TODO vllt schauen wegen Typproblemen
            return new ShopLevel(variable, varType, compareTo);
        }
    }

    /**
     * Diese Methode gibt den Klassennamen aus einer Klassendeklaration zurück (
     * class ASDF \{ wird zu ASDF).
     *
     * @param textzeile Die Zeile mit dem Klassennamen.
     *
     * @return Der Klassenname.
     */
    private String getClassname(String textzeile)
    {
        String classname = textzeile.replaceAll("\\s+", "");
        return classname.substring(5, classname.length() - 1);
    }

    private String getText(String textzeile)
    {
        return textzeile.substring(textzeile.indexOf("\""), textzeile.indexOf("\"", textzeile.indexOf("\"") + 1));
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
            } else
            {
                switch (state)
                {
                    case nichts:
                        this.kategorie = new ShopKategorie(getClassname(textzeile));
                        this.state = LoadStates.Kategorie;
                        return true;
                    case Kategorie:
                        this.currentShop = new Shop(getClassname(textzeile));
                        this.state = LoadStates.Shop;
                        return true;
                    default:
                        LOG.warning("In der Zeile " + this.zeilennummer + " darf keine Klassendeklaration stehen(" + textzeile + ").");
                        return false;
                }
            }
        } else if (state == LoadStates.Shop)
        {
            return loadShop(textzeile);
        } else if (state == LoadStates.Items)
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
                this.zeilennummer++;
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
        if (textzeile.startsWith("{"))
        {
            createItem(textzeile);
            return true;
        } else if (textzeile.startsWith("};"))
        {
            this.currentShop.addShopItems(currentShopItems);
            this.state = LoadStates.Kategorie;
            return true;
        }
        LOG.warning("Die Zeile " + this.zeilennummer + " kann nicht als Inhalt eines Itemsets eines Shops interpretiert werden (" + textzeile + ").");
        return false;
    }

    private boolean loadShop(String textzeile)
    {
        if (textzeile.startsWith("title"))
        {
            this.currentShop.addTitle(getText(textzeile));
            return true;
        } else if (textzeile.startsWith("license"))
        {
            this.currentShop.addLicense(getText(textzeile));
            return true;
        } else if (textzeile.startsWith("side"))
        {
            this.currentShop.addSide(getText(textzeile));
            return true;
        } else if (textzeile.startsWith("level"))
        {
            this.currentShop.addShopLevel(createLevel(textzeile));
            return true;
        } else if ((textzeile.replaceAll("\\s+", "")).contains("[]={"))//uniforms, headgear, goggles,...
        {
            this.currentShopItems = new ShopItems(textzeile.substring(0, textzeile.indexOf("[]") - 1));
            this.state = LoadStates.Items;
            return true;
        } else if (textzeile.startsWith("};"))
        {
            this.kategorie.addShop(currentShop);
            this.state = LoadStates.Kategorie;
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
