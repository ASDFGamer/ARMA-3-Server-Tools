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
package org.asdfgamer.arma_tools.model.dbData;

import java.util.Date;

/**
 * Dies hält alle Daten für einen Spieler.
 * @author ASDFGamer
 */
public class Player
{
    private final int uid;
    
    private final String name;
    
    private String[] aliases;
    
    private final long playerid;
    
    private int cash;
    
    private int bankacc;
    
    private int coplevel;
    
    private int mediclevel;
    
    private String[] civ_licenses;
    
    private String[] cop_licenses;
    
    private String[] med_licenses;
    
    private String[] civ_gear;
    
    private String[] cop_gear;
    
    private String[] med_gear;
    
    private double[] civ_stats;
    
    private double[] cop_stats;
    
    private double[] med_stats;
    
    private int arrested;
    
    private int adminlevel;
    
    private int donorlevel;
    
    private int blacklist;
    
    private int civ_alive;
    
    private double[] civ_position;
    
    private int[] playtime;
    
    private Date insert_time;
    
    private Date last_seen;
    
    public Player(String name, int uid, long playerid)
    {
        this.name = name;
        this.uid = uid;
        this.playerid = playerid;
    }
}
