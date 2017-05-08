/*
 */
package org.asdfgamer.arma_tools.controll.pbo_extract;

import org.asdfgamer.hilfreich.FileUtil;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 *
 * @author ASDFGamer
 */
public class PBOExtract
{
    private static String startdir;
    /**
     * @param args the command line arguments
     * @throws java.io.IOException TODO entfernen
     * @throws java.lang.InterruptedException TODO entfernen
     */
    public static void main(String[] args) throws IOException, InterruptedException
    {
        startdir = System.getProperty("user.dir");
        System.out.println(startdir);
        extractInFolder(new File(startdir));
    }

    public static boolean extractInFolder(File folder) throws IOException, InterruptedException
    {
        File[] list = folder.listFiles();
        for (int i = 0; i < list.length; i++)
        {
            if (FileUtil.isFolder(list[i]))
            {
                extractInFolder(list[i]);
            } else
            {
                if (list[i].toString().endsWith(".pbo"))
                {
                    Path Ordner = Paths.get(list[i].toString() + "ordner");
                    FileUtil.createFolder(Ordner);
                    String command = "cmd";
                    Process p = Runtime.getRuntime().exec(command);
                    new Thread(new SyncPipe(p.getErrorStream(), System.err)).start();
                    new Thread(new SyncPipe(p.getInputStream(), System.out)).start();
                    PrintWriter stdin = new PrintWriter(p.getOutputStream());

                    stdin.println("D:\\Test\\PBOConsole.exe -unpack \"" + list[i] + "\" \"" + Ordner.toString() + "\"");
                    stdin.close();
                    int returnCode = p.waitFor();
                    System.out.println("Return code = " + returnCode + " bei " + "D:\\Test\\PBOConsole.exe -unpack " + list[i] + " " + Ordner.toString());
                    System.out.println("Die Datei " + list[i] + " wurde entpackt.");
                }
            }
        }
        return true;
    }

}
