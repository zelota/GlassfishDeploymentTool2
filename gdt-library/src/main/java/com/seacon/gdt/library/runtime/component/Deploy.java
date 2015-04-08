package com.seacon.gdt.library.runtime.component;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1758/deploy-1/index.html
 *
 * @author varsanyi.peter
 */
public class Deploy extends GdtCommand {

    public Deploy(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("Deploy component");
        setCommandExecuteIndex(com.seacon.gdt.library.xml.Constants.CI_COMPONENT_DEPLOY);
    }

    public void setParameters(com.seacon.gdt.library.xml.objects.data.Component componentData, com.seacon.gdt.library.xml.objects.data.Component parentAppData, com.seacon.gdt.library.xml.objects.data.Domain domainData) throws URISyntaxException, Exception {
        String targetPort = (domainData == null || domainData.getAdminport() == null || domainData.getAdminport().isEmpty()) ? getTargetServer().getPort() : domainData.getAdminport();
        
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(targetPort);
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("deploy");
        
        if (componentData.getType() != null && !componentData.getType().isEmpty()) {
            getParameters().add("--type");
            getParameters().add(componentData.getType());
        }
        
        if (componentData.getContextroot() != null && !componentData.getContextroot().isEmpty()) {
            getParameters().add("--contextroot");
            getParameters().add(componentData.getContextroot());
        }

        if (componentData.getName()!= null && !componentData.getName().isEmpty()) {
            getParameters().add("--name");
            getParameters().add(componentData.getName());
        }
        
        if (componentData.getProperties().size() != 0) {
            getParameters().add("--property");
            
            String propStr = "";
            for (com.seacon.gdt.library.xml.objects.data.Property prop : componentData.getProperties()) {
                if (!"".equals(propStr)) {
                    propStr += ":";
                }
                propStr += prop.getName() + "=" + prop.getValue();
            }
            getParameters().add("\"" + propStr + "\"");
        }     
        
        getParameters().add(componentData.getPath());
    }

}
