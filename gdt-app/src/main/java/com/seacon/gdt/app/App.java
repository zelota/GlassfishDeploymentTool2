package com.seacon.gdt.app;

import com.seacon.gdt.app.main.Gdt;
import com.seacon.gdt.library.utility.GdtLog;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import java.io.File;
import java.net.URISyntaxException;

/**
 * Glassfish Deployment Tool.
 * 
 * A simple, easy tool for reaching Glassfish asadmin functions by an xml config file.
 * Not implemented ALL asadmin functions!
 * Implemented functions:
 * - Pool create, drop.
 * - JDBC resource create, drop.
 * - Component deploy, redeploy, delete, reload.
 * - Domain create, drop, star, stop.
 * 
 * Know bugs:
 * - Reload works just in localhost.
 * - Domain creation (messages) stucks after the creation.
 * 
 * Home:
 * "C:/Users/Peter/Documents/GitHub/GlassfishDeploymentTool/document/gdt_example.xml"
 * "C:/Users/Peter/Documents/GitHub/GlassfishDeploymentTool/document/configExamples/pool_create.xml"
 * "C:/Users/Peter/Documents/GitHub/GlassfishDeploymentTool/document/configExamples/pool_drop.xml"
 * 
 * Work:
 * "e:/Sajat/GitHub/GlassfishDeploymentTool/document/gdt_example.xml"
 * "e:/Sajat/GitHub/GlassfishDeploymentTool/document/configExamples/pool_create.xml"
 * "e:/Sajat/GitHub/GlassfishDeploymentTool/document/configExamples/pool_recreate.xml"
 * "e:/Sajat/GitHub/GlassfishDeploymentTool/document/configExamples/pool_drop.xml"
 * 
 * @author varsanyi.peter
 * @version 1.0
 * @since 2014.08.21
 */
public class App {
    
    public static void main(String[] args) {
        GdtLog.info("-===  Glassfish Deployment Tool begin.  ===-");
        
        if (isValidParameters(args)) {
            try {
                Gdt gdt = new Gdt(args[0]);
                gdt.processXml();
            } catch (Exception ex) {
                GdtLog.error(ex);
            }
        }
        
        try {
            PasswordFileHandler.deletePasswordFile();
        } catch (URISyntaxException ex) {
            GdtLog.error(ex);
        }
        
        GdtLog.info("-===  Glassfish Deployment Tool end.  ===-");
    }

    /**
     * Check parameters.
     * 
     * It checks:
     * - parameter count
     * - file is exist
     * 
     * @param args String[] from system.
     * @return Boolean.
     */
    private static Boolean isValidParameters(String[] args) {
        if (args.length != 1) {
            GdtLog.error("One (only one) parameter is mandatory.");
            return false;
        }
        GdtLog.info("Parameter: " + args[0]);
        
        if (!isFileExists(args[0])) {
            GdtLog.error("The given file path parameter is wrong, file doesn't exist or parameter is not a path.");
            return false;
        }
        return true;
    }

    /**
     * Given file (with path) is exist.
     * 
     * It checks:
     * - an existing file.
     * - not a directory.
     * 
     * @param string File name with path.
     * @return Boolean.
     */
    private static Boolean isFileExists(String fpath) {
        File tFile = new File(fpath);
        if (!tFile.exists()) {
            return false;
        }
        if (tFile.isDirectory()) {
            return false;
        }
        return true;
    }
    
}
