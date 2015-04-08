package com.seacon.gdt.library.runtime.domain;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 * http://docs.oracle.com/cd/E19798-01/821-1751/ggoco/index.html
 *
 * @author varsanyi.peter
 */
public class List extends GdtCommand {

    public List(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("List domains");
        setParameters();
    }

    private void setParameters() throws URISyntaxException {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("-u");
        getParameters().add(getTargetServer().getUser());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("list-domains");
    }

}
