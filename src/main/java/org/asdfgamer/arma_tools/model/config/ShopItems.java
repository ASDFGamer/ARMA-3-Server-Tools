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

import java.util.LinkedList;
import java.util.List;

/**
 * Dies ist eine Kategorie von Items die in einem Shop vorhanden sien können.
 * @author ASDFGamer
 */
public class ShopItems
{
    private final String ITEMS_NAME;
    
    private List<ShopItem> items = new LinkedList<>();
    
    public ShopItems(String name)
    {
        this.ITEMS_NAME = name;
    }
    
    public boolean  addShopItem(ShopItem item)
    {
        return this.items.add(item);
    }

    public String getName()
    {
        return ITEMS_NAME;
    }

    public List<ShopItem> getItems()
    {
        return items;
    }
    
    public String save()
    {
        String result = "\t \t"+ this.ITEMS_NAME + "[] = {\n";
        for (ShopItem item : this.items)
        {
            result += item.save();
        }
        result = result.substring(0, result.length()-1);
        result += "\n \t \t};";
        return result;   
    }
    
    
}
