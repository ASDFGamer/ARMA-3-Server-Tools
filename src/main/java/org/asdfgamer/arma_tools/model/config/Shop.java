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
package org.asdfgamer.arma_tools.model.config;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Dies ist ein Shop (zur Zeit mit Attributen von Weapons und Clothing.
 * @author ASDFGamer
 */
public class Shop
{
    private final static Logger LOG = Logger.getLogger(Shop.class.getName());
    
    private final String SHOPNAME;
    
    private ConfigFile config;
    
    private ShopLevel shopLevel;
    
    private String title;
    
    private License license;
    
    private String side;
    
    private List<ShopItems> shopItems = new ArrayList<>();
    
    public Shop(String name)
    {
        this.SHOPNAME = name;
    }

    public boolean addShopLevel(ShopLevel shopLevel)
    {
        this.shopLevel = shopLevel;
        return true;
    }
    
    public boolean addTitle(String title)
    {
        this.title = title;
        return true;
    }
    
    public boolean addLicense(String license)
    {
        this.license = new License(license);
        return true;
    }
    
    public boolean addSide(String side)
    {
        this.side = side;
        return true;
    }
    
    public boolean addShopItems(ShopItems items)
    {
        return this.shopItems.add(items);
    }

    public String getShopName()
    {
        return SHOPNAME;
    }

    public ShopLevel getShopLevel()
    {
        return shopLevel;
    }

    public String getTitle()
    {
        return title;
    }

    public String getLicense()
    {
        return license.getLicense();
    }

    public String getSide()
    {
        return side;
    }

    public List<ShopItems> getShopItems()
    {
        return shopItems;
    }
    
    public String save()//TODO für jede Config anpassen
    {
        switch (this.config)
        {
            case Clothing:
                return saveClothing();
            default:
                LOG.warning("Der Typ " + this.config.name() + " ist noch nicht zum Speichern implementiert.");
                return null;
        }
        
    }
    
    private String saveClothing()
    {
        String result = "\t class " + this.SHOPNAME + " {\n";
        result +=       "\t \ttitle = \"" + this.title + "\";\n";
        result +=       "\t \tlicense = \"" + this.license.save() + "\";\n";
        result +=       "\t \tside = \"" + this.side + "\";\n";//TODO
        return null;
    }
}
