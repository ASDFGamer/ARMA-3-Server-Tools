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
 *
 * @author ASFDGamer
 */
public enum ConfigFile
{
    Clothing(true),
    Gather(false),
    Licenses(false),
    Master(false),
    Process(false),
    SpawnPoints(false),
    SpyGlass(false),
    Vehicles(false),
    vItems(false),
    Weapons(true);

    private boolean implemented;
    
    private ConfigFile(boolean implemented)
    {
        this.implemented = implemented;
    }
    
    public boolean isImplemented()
    {
        return this.implemented;
    }
}
