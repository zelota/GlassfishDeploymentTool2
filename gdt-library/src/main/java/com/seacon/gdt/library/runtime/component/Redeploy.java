package com.seacon.gdt.library.runtime.component;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1758/redeploy-1/index.html
 *
 * @author varsanyi.peter
 */
public class Redeploy extends GdtCommand {

    public Redeploy(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("Redeploy component");
        setCommandExecuteIndex(com.seacon.gdt.library.xml.Constants.CI_COMPONENT_REDEPLOY);
    }

    public void setParameters(com.seacon.gdt.library.xml.objects.data.Component componentData, com.seacon.gdt.library.xml.objects.data.Domain domainData) throws URISyntaxException, Exception {
        String targetPort = (domainData == null || domainData.getAdminport() == null || domainData.getAdminport().isEmpty()) ? getTargetServer().getPort() : domainData.getAdminport();
        
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(targetPort);
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("redeploy");
        
        getParameters().add("--name");
        getParameters().add(componentData.getName());
        
        getParameters().add(componentData.getPath());
    }

}
