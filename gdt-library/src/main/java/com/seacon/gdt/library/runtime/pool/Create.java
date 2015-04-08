package com.seacon.gdt.library.runtime.pool;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.data.Pool;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1751/gharo/index.html
 *
 * @author varsanyi.peter
 */
public class Create extends GdtCommand {

    public Create(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Create pool");
        setCommandExecuteIndex(com.seacon.gdt.library.xml.Constants.CI_POOL_CREATE);
    }

    public void setParameters(Pool poolData) throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("create-jdbc-connection-pool");

        getParameters().add("--datasourceclassname");
        getParameters().add(poolData.getDatasourceclassname());
        getParameters().add("--restype");
        getParameters().add(poolData.getRestype());
                
        if (poolData.getProperties().size() != 0) {
            getParameters().add("--property");
            
            String propStr = "";
            for (com.seacon.gdt.library.xml.objects.data.Property prop : poolData.getProperties()) {
                if (!"".equals(propStr)) {
                    propStr += ":";
                }
                propStr += prop.getName() + "=" + prop.getValue();
            }
            getParameters().add("\"" + propStr + "\"");
        }
        
        getParameters().add(poolData.getJndiName());

    }

}
