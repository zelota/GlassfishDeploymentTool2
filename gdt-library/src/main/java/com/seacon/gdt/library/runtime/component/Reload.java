package com.seacon.gdt.library.runtime.component;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.GdtLog;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.data.Component;
import com.seacon.gdt.library.xml.objects.data.Domain;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.io.File;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E18930_01/html/821-2417/gilfm.html#fwakh
 *
 * @author varsanyi.peter
 */
public class Reload extends GdtCommand {

    public Reload(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("Reload component");
        setCommandExecuteIndex(com.seacon.gdt.library.xml.Constants.CI_COMPONENT_RELOAD);
    }

    public void setParameters(com.seacon.gdt.library.xml.objects.data.Component componentData, com.seacon.gdt.library.xml.objects.data.Domain domainData, String domainsrootdir) throws URISyntaxException, Exception {
        getParameters().clear();
        String targetDir = getComponentDirectory(componentData.getName(), domainData.getName(), domainsrootdir);
        String osName = System.getProperty("os.name");
        
        GdtLog.info("Reload info - the system OS name: " + osName + " - targetDir: " +  targetDir);
        
        if (osName.toLowerCase().contains("windows")) {
            getParameters().add("cmd");
            getParameters().add("/c");
            getParameters().add("\"echo > " + targetDir + ".reload\"");
        }
        if (osName.toLowerCase().contains("linux") || osName.toLowerCase().contains("unix")) {
            getParameters().add("touch ");
            getParameters().add(targetDir + ".reload");
        }
        
    }

    private String getComponentDirectory(String componentName, String domainName, String domainsrootdir) {
        String retVal = domainsrootdir;
        if (!retVal.endsWith(File.separator)) {
            retVal += File.separator;
        }
        retVal += domainName + File.separator + "applications" + File.separator + componentName + File.separator;
        return retVal;
    }


}
