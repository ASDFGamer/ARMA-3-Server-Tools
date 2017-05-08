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
package org.asdfgamer.arma_tools.datatools;

import org.asdfgamer.arma_tools.controll.mySQL.MySQLTools;
import org.asdfgamer.arma_tools.controll.config.Einstellungen;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ASDFGamer
 */
public class LoadMySQLTest
{
    
    MySQLTools loadMySQL = null;
    
    public LoadMySQLTest()
    {
    }
    
    @BeforeClass
    public static void setUpClass()
    {
        
    }
    
    @AfterClass
    public static void tearDownClass()
    {
    }
    
    @Before
    public void setUp()
    {
        loadMySQL = new MySQLTools("jdbc:mysql://"+Einstellungen.serverip.getWert()+"/"+Einstellungen.mySQLStruktur.getWert() + "?"
        + "user=" + Einstellungen.mySQLBenutzername.getWert() + "&password=" + Einstellungen.mySQLPasswort.getWert());
    }
    
    @After
    public void tearDown()
    {
    }

    @Test
    public void testInit()
    {
        
    }
    
    @Test
    public void testGetInfos()
    {
        System.out.println("infos");
        String expResult = null;
        String result = loadMySQL.getInfos();
        System.out.println(result);
        assertNotEquals(expResult, result);
    }
    
    
    
}
