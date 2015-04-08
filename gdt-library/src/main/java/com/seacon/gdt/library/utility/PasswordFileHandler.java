package com.seacon.gdt.library.utility;

import java.io.File;
import java.io.PrintWriter;
import java.net.URISyntaxException;

/**
 *
 * @author varsanyi.peter
 */
public class PasswordFileHandler {
    
    public static String getPasswordFilePath() throws URISyntaxException {
        return getDomainPasswordFilePath("");
    }

    public static void createPasswordFile(String password, String apwd, String upwd, String mpwd) throws Exception {
        File tFile = new File(getPasswordFilePath());
        PrintWriter writer = new PrintWriter(tFile, "UTF-8");
        writer.println("AS_ADMIN_PASSWORD=" + password);
        writer.println("AS_ADMIN_ADMINPASSWORD=" + apwd);
        writer.println("AS_ADMIN_USERPASSWORD=" + upwd);
        writer.println("AS_ADMIN_MASTERPASSWORD=" + mpwd);       
        writer.close();
        writer = null;
        tFile = null;
    }
    
    public static void deletePasswordFile() throws URISyntaxException {
        File tFile = new File(getPasswordFilePath());
        if (tFile.exists()) {
            tFile.delete();
        }
    }
    
    public static String getDomainPasswordFilePath(String domainName) throws URISyntaxException {
        String osName = System.getProperty("os.name");
        String retVal = PasswordFileHandler.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath() + "password" + domainName + ".txt";
        if (osName.toLowerCase().contains("windows")) {
            retVal = retVal.substring(1);  // cut the first / from string's beginning
        }
        return retVal;
    }    
    
    public static void createDomainPasswordFile(String domainName, String password, String apwd, String upwd, String mpwd) throws Exception {
        File tFile = new File(getDomainPasswordFilePath(domainName));
        PrintWriter writer = new PrintWriter(tFile, "UTF-8");
        writer.println("AS_ADMIN_PASSWORD=" + password);
        writer.println("AS_ADMIN_ADMINPASSWORD=" + apwd);
        writer.println("AS_ADMIN_USERPASSWORD=" + upwd);
        writer.println("AS_ADMIN_MASTERPASSWORD=" + mpwd);       
        writer.close();
        writer = null;
        tFile = null;
    }
    
    public static void deleteDomainPasswordFile(String domainName) throws URISyntaxException {
        File tFile = new File(getDomainPasswordFilePath(domainName));
        if (tFile.exists()) {
            tFile.delete();
        }
    }    
    
}
