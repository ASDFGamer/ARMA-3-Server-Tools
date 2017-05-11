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

/**
 * Dies ist ein Item in einem Shop.
 * @author ASDFGamer
 */
public class ShopItem
{
    private final String itemid;
    
    private final String itemname;
    
    private final int price;
    
    private final int sellprice;
    
    private final ShopLevel level;
    
    public ShopItem(String item, String itemname, int price, int sellprice)
    {
        this.itemid =item;
        this.itemname = itemname;
        this.price = price;
        this.sellprice = sellprice;
        this.level = null;
    }
    
    public ShopItem(String item, String itemname, int price, ShopLevel level)
    {
        this.itemid =item;
        this.itemname = itemname;
        this.price = price;
        this.sellprice = Integer.MIN_VALUE;
        this.level = level;
    }

    public String getItemID()
    {
        return itemid;
    }

    public String getItemname()
    {
        return itemname;
    }

    public int getPrice()
    {
        return price;
    }

    public int getSellprice()
    {
        return sellprice;
    }

    public ShopLevel getLevel()
    {
        return level;
    }
    
    public String save()//TODO testen ob die beiden Formate für Itemnotierungen untereinader kompatible sind.
    {
        if (this.level == null)
        {
            return "\t \t \t{ \"" + itemid + "\", \"" +itemname+"\", " + price + ", " + sellprice + " },";
        }
        else 
        {
            return "\t \t \t{ \"" + itemid + "\", \"" +itemname+"\", " + price + ", " + level.save() + " },";
        }
                
    }
    
}
