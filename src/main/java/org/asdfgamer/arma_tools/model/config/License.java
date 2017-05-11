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
 * Dies stellt eine License da.
 * @author ASDFGamer
 */
public class License
{
    private String licensename;

    public String getLicense()
    {
        return licensename;
    }
    
    public License(String name) //TODO überprüfen ob es die License gibt.
    {
        this.licensename=name;
    }
    
    /**
     * Diese Methode passt den Inhalt wieder so an, dass er in eine .hpp geschrieben werden kann.
     * @return 
     */
    public String save()
    {
        return "\"" + licensename + "\"";
    }
}
