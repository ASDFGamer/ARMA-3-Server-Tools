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
 * Dies ist ein Level, welches im Shop angegeben werden kann.
 * @author ASDFGamer
 */
public class ShopLevel
{
    private String level;
    
    private String varName;
    
    private VarType type;
    
    private String errorMessage = null;
    
    public ShopLevel(String[] levelInfo)
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    public ShopLevel(String variablename, VarType vartype, String compareTo)
    {
        this.varName = variablename;
        this.type = vartype;
        this.level = compareTo;
    }
    
    public ShopLevel(String variablename, VarType vartype, String compareTo, String errortext)
    {
        this.varName = variablename;
        this.type = vartype;
        this.level = compareTo;
        this.errorMessage = errortext;
    }
    
    public ShopLevel(int level)
    {
        this.level = ""+level;
    }

    public String getLevel()
    {
        return level;
    }

    public String getVarName()
    {
        return varName;
    }

    public VarType getType()
    {
        return type;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }

    String save() //TODO testen ob die beiden Formate unterienander funktionieren
    {
        if (this.errorMessage == null)
        {
            return "{ \"" + this.varName + "\", \"" + this.type.name() + "\", " + this.level + " }";
        }
        else
        {
            return "{ \"" + this.varName + "\", \"" + this.type.name() + "\", " + this.level + ", \"" + this.errorMessage +"\" }";
        }
    }
    
}
