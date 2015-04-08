package com.seacon.gdt.library.runtime.domain;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.data.Domain;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1751/ggoch/index.html
 * 
 * @author varsanyi.peter
 */
public class Stop extends GdtCommand {
    
    public Stop(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Stop domain");
        setCommandExecuteIndex(com.seacon.gdt.library.xml.Constants.CI_DOMAIN_STOP);
    }

    public void setParameters(Domain domainData) throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(domainData.getAdminport());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("stop-domain");

        getParameters().add(domainData.getName());
    }
    
}
