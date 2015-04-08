package com.seacon.gdt.library.runtime.server;

import com.seacon.gdt.library.runtime.GdtCommand;
import com.seacon.gdt.library.utility.PasswordFileHandler;
import com.seacon.gdt.library.xml.objects.servers.Target;
import java.net.URISyntaxException;

/**
 *
 * @author varsanyi.peter
 */
public class Version extends GdtCommand {

    public Version(String asadminPath, Target targetServer) throws URISyntaxException {
        super(asadminPath, targetServer);
        setProcessInfo("Server version");
        setParameters();
    }

    private void setParameters() throws URISyntaxException  {
        getParameters().add("-H");
        getParameters().add(getTargetServer().getHost());
        getParameters().add("-p");
        getParameters().add(getTargetServer().getPort());
        getParameters().add("--passwordfile");
        getParameters().add("\"" + PasswordFileHandler.getPasswordFilePath() + "\"");

        getParameters().add("version");
        getParameters().add("-v");
    }

    public Boolean isServerRunning() {
        Boolean retVal = true;
        
        for (int i = 0; i < getOutputLines().size() && retVal == true; i++) {
            if (getOutputLines().get(i).contains("Version string could not be obtained")) {
                retVal = false;
            }
        }
        
        return (getOutputLines().size() != 0 && retVal);
    }

}
