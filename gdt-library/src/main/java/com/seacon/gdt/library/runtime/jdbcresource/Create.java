package com.seacon.gdt.library.runtime.jdbcresource;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.data.Jdbcresource;
import com.seacon.gdt.library.xml.objects.data.Pool;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19776-01/820-4497/6nfv6jlj0/index.html
 *
 * @author varsanyi.peter
 */
public class Create extends GdtCommand {

    public Create(String asadminPath, Target targetServer) {
        super(asadminPath, targetServer);
        setProcessInfo("Create JDBC resource");
        setCommandExecuteIndex(com.seacon.gdt.library.xml.Constants.CI_JDBCRESOURCE_CREATE);
    }

    public void setParameters(Jdbcresource jdbcrData, Pool poolData) throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("create-jdbc-resource");

        getParameters().add("--connectionpoolid");
                
        getParameters().add(poolData.getJndiName());
        
        getParameters().add(jdbcrData.getName());
    }

}
