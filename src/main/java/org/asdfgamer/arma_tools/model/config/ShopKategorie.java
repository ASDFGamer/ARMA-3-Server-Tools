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

/**
 * Dies enthält alle Shops die in der Kategorie sind (in der Configfile).
 * @author ASDFGamer
 */
public class ShopKategorie
{
    private final String KATEGORIE_NAME;
    
    private final List<Shop> SHOPS = new ArrayList<>();
    
    public ShopKategorie(String name)
    {
        this.KATEGORIE_NAME = name;
    }
    
    public boolean addShop(Shop shop)
    {
        return this.SHOPS.add(shop);
    }
    
    public List<Shop> getShops()
    {
        return this.SHOPS;
    }
}
