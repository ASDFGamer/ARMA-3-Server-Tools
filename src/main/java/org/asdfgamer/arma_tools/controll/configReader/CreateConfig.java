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
package org.asdfgamer.arma_tools.controll.configReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import static java.util.Collections.list;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asdfgamer.arma_tools.controll.config.Einstellungen;
import org.asdfgamer.hilfreich.FileUtil;

/**
 * Mit dieser Klasse werden all Config Dateien in dem angegebenen Ordner (und
 * Unterordnern) zu cpp umgewandelt und TODO zusammengefügt.
 *
 * @author ASDFGamer
 */
public class CreateConfig
{

    private boolean runCmd(String Datei)
    {
        try
        {
            String command = "cmd";
            Process p = Runtime.getRuntime().exec(command);
            new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
            new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
            PrintWriter stdin = new PrintWriter(p.getOutputStream());
            stdin.println();//TODO
            stdin.close();//TODO
            int returnCode = p.waitFor();
        } catch (IOException ex)
        {
            Logger.getLogger(CreateConfig.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        } catch (InterruptedException ex)
        {
            Logger.getLogger(CreateConfig.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
        return true;
    }
}
